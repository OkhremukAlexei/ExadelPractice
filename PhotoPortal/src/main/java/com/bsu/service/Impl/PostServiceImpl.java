package com.bsu.service.Impl;

import com.bsu.entity.PhotoPost;
import com.bsu.repository.PostCollectionSingleton;
import com.bsu.service.PostsService;
import com.google.gson.Gson;

import java.util.*;

public class PostServiceImpl implements PostsService {
    private List<PhotoPost> collection;

    public PostServiceImpl(){
        this.collection = PostCollectionSingleton.getInstance().getCollection();
    }

    public List<PhotoPost> getPhotoPosts(int skip, int top, Map<String, String> filter) {
        List<PhotoPost> filterPosts = new ArrayList<>();
        for(Map.Entry pair : filter.entrySet()) {
            if (pair.getKey().equals("author")) {
                collection.stream()
                        .filter(post -> post.getAuthor().equals(pair.getValue()))
                        .forEach(filterPosts::add);
            }
            else if (pair.getKey().equals("creationDate")) {
                collection.stream()
                        .filter(post -> post.getCreationDate().equals(pair.getValue()))
                        .forEach(filterPosts::add);
            }
        }
        if(filter.size() == 0) {
            filterPosts = new ArrayList<>(collection);
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

    public PhotoPost getPhotoPost(int id) {
        for (PhotoPost post: collection) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean validatePhotoPost(PhotoPost post) {
        for(PhotoPost photoPost : collection) {
            if (photoPost.getId() == post.getId()) {
                return false;
            }
        }
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

    public boolean addPhotoPost(PhotoPost post) {
        if(validatePhotoPost(post)) {
            collection.add(post);
            return true;
        }
        else
            return false;
    }

    public boolean editPhotoPost(int id, PhotoPost postFilter) {
        PhotoPost post = getPhotoPost(id);
        if(post == null) {
            return false;
        }
        if (postFilter.getDescription() != null && postFilter.getDescription().length() <= 200) {
            post.setDescription(postFilter.getDescription());
        }
        if (postFilter.getHashtags() != null) {
            post.setHashtags(postFilter.getHashtags());
        }
        return true;
    }

    public boolean removePhotoPost(int id) {
        PhotoPost post = getPhotoPost(id);
        if(post != null) {
            collection.remove(post);
            return true;
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
        PhotoPost post = this.getPhotoPost(id);
        post.getLikes().add(user);
    }

    public void removeLike(int id, String user) {
        PhotoPost post = this.getPhotoPost(id);
        int index = post.getLikes().indexOf(user);
        if (index != -1) post.getLikes().remove(user);
    }

    public boolean isHasUserLike(int id, String user) {
        PhotoPost post = this.getPhotoPost(id);
        return post.getLikes().contains(user);
    }

    public static StringBuilder buildCurrentDate(){
        Calendar calendar = GregorianCalendar.getInstance();
        StringBuilder date = new StringBuilder();
        date.append(calendar.get(Calendar.DATE))
                .append("-").append(calendar.get(Calendar.MONTH) + 1)
                .append("-").append(calendar.get(Calendar.YEAR));
        return date;
    }

    public String toJsonString(List<PhotoPost> list)
    {
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
