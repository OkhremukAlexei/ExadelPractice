package com.bsu.service;

import com.bsu.model.PhotoPost;

import java.util.List;
import java.util.Map;

public interface PostsService {
    List<PhotoPost> getPhotoPosts(int skip, int top, Map<String, Object> filter);
    PhotoPost getPhotoPost(int id);
    boolean validatePhotoPost(PhotoPost post);
    boolean addPhotoPost(PhotoPost post);
    boolean editPhotoPost(int id, Map<String, Object> map);
    boolean removePhotoPost(int id);
}
