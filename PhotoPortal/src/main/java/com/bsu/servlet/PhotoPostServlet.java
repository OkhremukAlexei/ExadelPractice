package com.bsu.servlet;

import com.bsu.entity.PhotoPost;
import com.bsu.service.Impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoPostServlet extends HttpServlet {
    private PostServiceImpl collection = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PhotoPost post = collection.getPhotoPost(id);
        if (post != null) {
            response.getOutputStream().println(post.toString());
        } else {
            response.getOutputStream().println("Not found\n");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String photoLink = request.getParameter("photoLink");
        String creationDate = PostServiceImpl.buildCurrentDate().toString();

        PhotoPost post = new PhotoPost(id, author, description, photoLink, creationDate, new ArrayList<>(), new ArrayList<>());

        if (collection.addPhotoPost(post)) {
            response.getOutputStream().println("Post has been successfully added\n");
        } else {
            response.getOutputStream().println("Invalid post\n");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (collection.removePhotoPost(id)) {
            response.getOutputStream().println("Post has been successfully deleted\n");
        } else {
            response.getOutputStream().println("Not found\n");
        }
    }
}
