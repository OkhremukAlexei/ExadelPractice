package com.bsu.service.Impl;

import com.bsu.DAO.Impl.PhotoPostDao;
import com.bsu.model.PhotoPost;
import com.bsu.service.PostsService;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DBPostServiceImpl implements PostsService {
    private PhotoPostDao photoPostDao;

    public DBPostServiceImpl(){
        this.photoPostDao = new PhotoPostDao();
    }

    public PhotoPostDao getPhotoPostDao() {
        return photoPostDao;
    }

    @Override
    public List<PhotoPost> getPhotoPosts(int skip, int top, Map<String, Object> filter) {
        List<PhotoPost> posts = photoPostDao.getAll();
        List<PhotoPost> filterPosts = new ArrayList<>();

        for(Map.Entry<String, Object> pair : filter.entrySet()) {
            switch (pair.getKey()){
                case "author":
                    posts.stream()
                            .filter(post -> post.getAuthor().equals(pair.getValue()))
                            .forEach(filterPosts::add);
                    break;
                case "creationDate":
                    posts.stream()
                            .filter(post -> post.getCreationDate().equals(pair.getValue()))
                            .forEach(filterPosts::add);
                    break;
                case "hashtags":
                    for(PhotoPost post : posts) {
                        for(String hashtag : post.getHashtags()) {
                            if(hashtag.equals(pair.getValue())) {
                                filterPosts.add(post);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        if(filter.size() == 0) {
            filterPosts = new ArrayList<>(posts);
        }
        if(top > filterPosts.size()) {
            top = filterPosts.size();
        } else if(top > 10) {
            top = 10;
        }
        if (skip >= filterPosts.size()) {
            skip = 0;
        }
        if (skip + top > filterPosts.size()) {
            return filterPosts.subList(skip, filterPosts.size());
        } else {
            return filterPosts.subList(skip, skip + top);
        }
    }

    @Override
    public PhotoPost getPhotoPost(int id) {
        return photoPostDao.get(id);
    }

    @Override
    public boolean validatePhotoPost(PhotoPost post) {
        if(photoPostDao.get(post.getId()) != null)
            return false;
        if(post.getAuthor() == null)
            return false;
        if(post.getDescription() == null || post.getDescription().length() > 200)
            return false;
        if(post.getPhotoLink() == null)
            return false;
        if(post.getCreationDate() == null || !isCorrectDate(post.getCreationDate()))
            return false;
        // Массив лайков и хэштегов должен быть пуст, но не null
        if(post.getLikes() == null || post.getHashtags() == null)
            return false;

        return true;
    }

    @Override
    public boolean addPhotoPost(PhotoPost post) {
        if(validatePhotoPost(post)) {
            return photoPostDao.save(post);
        }
        else
            return false;
    }

    @Override
    public boolean editPhotoPost(int id, Map<String, String> map) {
        PhotoPost post = photoPostDao.get(id);
        if(post == null) {
            return false;
        }

        String description = "";
        String hashtags = "";

        for(Map.Entry<String, String> pair : map.entrySet()) {
            switch (pair.getKey()) {
                case "description":
                    description = String.valueOf(pair.getValue());
                    break;
                case "hashtags":
                    hashtags = String.valueOf(pair.getValue());
                    break;
            }
        }

        Map<String, String> params = new HashMap<>();
        if (description != null && description.length() <= 200) {
            params.put("description", description);
        } else {
            params.put("description", "");
        }
        params.put("hashtags", hashtags);

        return photoPostDao.update(id, params);
    }

    @Override
    public boolean removePhotoPost(int id) {
        PhotoPost post = getPhotoPost(id);
        if(post != null) {
            return photoPostDao.delete(post);
        }else {
            return false;
        }
    }

    private boolean isCorrectDate(String date) {
        String[] tempDate;
        if(date.contains("-")) {
            tempDate = date.split("-");
        } else if(date.contains("."))
            tempDate= date.split(".");
        else
            return false;
        if(tempDate.length != 3) {
            return false;
        }
        int day, month, year;
        day = Integer.parseInt(tempDate[0]);
        month = Integer.parseInt(tempDate[1]) - 1;
        year = Integer.parseInt(tempDate[2]);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);

        return calendar.get(Calendar.DATE) == day &&
                calendar.get(Calendar.MONTH) == month &&
                calendar.get(Calendar.YEAR) == year;
    }

    public void addLike(int id, String user) {
        photoPostDao.addLike(id, user);
    }

    public void removeLike(int id, String user) {
        photoPostDao.removeLike(id, user);
    }

    public boolean isHasUserLike(int id, String user) {
        return photoPostDao.isHasUserLike(id, user);
    }

    public static java.sql.Date toSqlDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return java.sql.Date.valueOf(date);
    }

    public static String buildCurrentDate() {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public String toJsonString(List<PhotoPost> list) {
        if(list.size() > 0) {
            Gson gson = new Gson();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (PhotoPost post : list) {
                sb.append(gson.toJson(post)).append(",");
            }
            sb.append("]");
            return sb.toString().replace(",]", "]");
        }
        return "";
    }
}
