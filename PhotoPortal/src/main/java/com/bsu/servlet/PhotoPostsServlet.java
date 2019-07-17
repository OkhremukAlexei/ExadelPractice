package com.bsu.servlet;

import com.bsu.entity.PhotoPost;
import com.bsu.service.Impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoPostsServlet extends HttpServlet {
    private PostServiceImpl collection = new PostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int top, skip;
        try {
            top = Integer.parseInt(request.getParameter("top"));
        }catch (Exception e) {
            top = 10;
        }

        try{
            skip = Integer.parseInt(request.getParameter("skip"));
        }catch (Exception e) {
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
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(collection.toJsonString(res));
        }catch (Exception e) {
            response.getOutputStream().println("Not found");
        }
    }
}
