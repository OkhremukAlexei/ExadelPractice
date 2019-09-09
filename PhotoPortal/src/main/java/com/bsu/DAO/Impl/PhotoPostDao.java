package com.bsu.DAO.Impl;

import com.bsu.DAO.Dao;
import com.bsu.model.PhotoPost;
import com.bsu.service.Impl.ConnectionPoolImpl;
import com.bsu.service.Impl.DBPostServiceImpl;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PhotoPostDao implements Dao<PhotoPost> {

    private Connection connection;
    public PhotoPostDao(){
        this.connection = ConnectionPoolImpl.getPool().getConnection();
    }
    public Connection getConnection() {
        return connection;
    }

    @Override
    public PhotoPost get(long id) {
        String sql = "SELECT * FROM photo_post " +
                "WHERE post_id = (?);";
        PhotoPost post = new PhotoPost();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            post.setId(rs.getInt("post_id"));
            post.setDescription(rs.getString("description"));
            post.setCreationDate(rs.getString("creation_date"));
            post.setPhotoLink(rs.getString("photo_link"));
            post.setAuthor(rs.getString("author"));
            post.setLikes(new ArrayList<>());
            post.setHashtags(new ArrayList<>());
        }catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT likes.post_id AS post_id, name FROM likes " +
                "JOIN photo_post ON photo_post.post_id = likes.post_id " +
                "WHERE likes.post_id = (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if(id == rs.getInt("post_id")) {
                    post.getLikes().add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT hashtags.post_id AS post_id, name FROM hashtags " +
                "JOIN photo_post ON photo_post.post_id = hashtags.post_id " +
                "WHERE hashtags.post_id = (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                post.getHashtags().add(rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public List<PhotoPost> getByDate(String date) {
        String sql = "SELECT * FROM photo_portal.photo_post WHERE creation_date = (?);";
        List<PhotoPost> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            stm.setDate(1, DBPostServiceImpl.toSqlDate(date));

            while (rs.next()) {
                PhotoPost post = new PhotoPost();
                post.setId(rs.getInt("post_id"));
                post.setDescription(rs.getString("description"));
                post.setCreationDate(rs.getString("creation_date"));
                post.setPhotoLink(rs.getString("photo_link"));
                post.setAuthor(rs.getString("author"));
                post.setLikes(new ArrayList<>());
                post.setHashtags(new ArrayList<>());
                list.add(post);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        sql = "SELECT likes.post_id AS post_id, name FROM likes " +
                "JOIN photo_post ON photo_post.post_id = likes.post_id ";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                for(PhotoPost post : list) {
                    if(post.getId() == rs.getInt("post_id")) {
                        post.getLikes().add(rs.getString("name"));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT hashtags.post_id AS post_id, name FROM hashtags " +
                "JOIN photo_post ON photo_post.post_id = hashtags.post_id ";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                for(PhotoPost post : list) {
                    if(post.getId() == rs.getInt("post_id")) {
                        post.getHashtags().add(rs.getString("name"));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getSize() {
        String sql = "SELECT COUNT(*) AS amount FROM photo_post;";
        int amount = 0;
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            rs.next();
            amount = rs.getInt("amount");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    @Override
    public List<PhotoPost> getAll() {
        String sql = "SELECT * FROM photo_portal.photo_post ORDER BY creation_date DESC;";
        List<PhotoPost> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                PhotoPost post = new PhotoPost();
                post.setId(rs.getInt("post_id"));
                post.setDescription(rs.getString("description"));
                post.setCreationDate(rs.getString("creation_date"));
                post.setPhotoLink(rs.getString("photo_link"));
                post.setAuthor(rs.getString("author"));
                post.setLikes(new ArrayList<>());
                post.setHashtags(new ArrayList<>());
                list.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT likes.post_id AS post_id, name FROM likes " +
                "JOIN photo_post ON photo_post.post_id = likes.post_id ";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                for(PhotoPost post : list) {
                    if(post.getId() == rs.getInt("post_id")) {
                        post.getLikes().add(rs.getString("name"));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT hashtags.post_id AS post_id, name FROM hashtags " +
                "JOIN photo_post ON photo_post.post_id = hashtags.post_id ";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                for(PhotoPost post : list) {
                    if(post.getId() == rs.getInt("post_id")) {
                        post.getHashtags().add(rs.getString("name"));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(PhotoPost post) {
        String sql = "INSERT INTO photo_post(post_id, description, creation_date, photo_link, author) " +
                "VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, post.getId());
            stm.setString(2, post.getDescription());
            stm.setDate(3, DBPostServiceImpl.toSqlDate(post.getCreationDate()));
            stm.setString(4, post.getPhotoLink());
            stm.setString(5, post.getAuthor());
            stm.executeUpdate();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }

        sql = "INSERT INTO hashtags(name, post_id) " +
                "VALUES (?, ?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            for(String hashtag : post.getHashtags()){
                stm.setString(1, hashtag);
                stm.setLong(2, post.getId());
                stm.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(long id, Map<String, Object> params) {
        String sql = "DELETE FROM likes WHERE likes.post_id = (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        sql = "DELETE FROM hashtags WHERE hashtags.post_id = (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        for(Map.Entry<String, Object> pair : params.entrySet()) {
            switch (pair.getKey()) {
                case "description":
                    sql = "UPDATE photo_post SET description = (?), creation_date = (?)" +
                            "WHERE photo_post.post_id = (?);";
                    try (PreparedStatement stm = connection.prepareStatement(sql)) {
                        stm.setString(1, pair.getValue().toString());
                        stm.setDate(2, DBPostServiceImpl.toSqlDate(DBPostServiceImpl.buildCurrentDate()));
                        stm.setLong(3, id);
                        stm.executeUpdate();
                    }catch (SQLException | ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                    break;
                case "hashtags":
                    sql = "INSERT INTO hashtags(name, post_id) " +
                            "VALUES (?, ?);";
                    try (PreparedStatement stm = connection.prepareStatement(sql)) {
                        for(Object hashtag : Arrays.asList(pair.getValue())){
                            stm.setString(1, hashtag.toString());
                            stm.setLong(2, id);
                            stm.executeUpdate();
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean delete(PhotoPost post) {
        String sql = "DELETE FROM likes WHERE likes.post_id = (?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, post.getId());
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        sql = "DELETE FROM hashtags WHERE hashtags.post_id = (?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, post.getId());
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        sql = "DELETE FROM photo_post WHERE photo_post.post_id = (?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, post.getId());
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void addLike(int id, String user) {
        String sql = "INSERT INTO likes(name, post_id) " +
                "VALUES (?, ?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user);
            stm.setLong(2, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLike(int id, String user) {
        String sql = "DELETE FROM likes " +
                "WHERE likes.name = (?) AND likes.post_id = (?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user);
            stm.setLong(2, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isHasUserLike(int id, String user) {
        String sql = "SELECT * FROM likes " +
                "WHERE likes.name = (?) AND likes.post_id = (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user);
            stm.setLong(2, id);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
