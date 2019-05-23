package com.bsu.servlet;

import com.bsu.entity.PhotoPost;
import com.bsu.service.Impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoPostsServlet extends HttpServlet {
    private PostServiceImpl collection = new PostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int top, skip;
        if(request.getParameter("top") != null){
            top = Integer.parseInt(request.getParameter("top"));
        }else {
            top = 10;
        }
        if(request.getParameter("skip") != null){
            skip = Integer.parseInt(request.getParameter("skip"));
        }else {
            skip = 0;
        }

        String author = request.getParameter("author");
        String creationDate = request.getParameter("creationDate");
        Map<String, String> filter = new HashMap<>();
        if(author != null) {
            filter.put("author", author);
        }
        if (creationDate != null) {
            filter.put("creationDate", creationDate);
        }
        List<PhotoPost> res = new ArrayList<>(collection.getPhotoPosts(skip, top, filter));
        if(res.size() == 0) {
            response.getOutputStream().println("Not found");
        }else {
            response.getOutputStream().println(collection.toJsonString(res));
        }
    }
}
