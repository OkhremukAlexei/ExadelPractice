package com.bsu.servlet;

import com.bsu.model.PhotoPost;
import com.bsu.service.Impl.ConnectionPoolImpl;
import com.bsu.service.Impl.DBPostServiceImpl;

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
        String hashtag = request.getParameter("hashtags");
        Map<String, Object> filter = new HashMap<>();
        if(author != null) {
            filter.put("author", author);
        }
        if (creationDate != null) {
            filter.put("creationDate", creationDate);
        }
        if (hashtag != null && !hashtag.equals("")) {
            filter.put("hashtags", "#" + hashtag);
        }

        DBPostServiceImpl collection = new DBPostServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<PhotoPost> res = new ArrayList<>(collection.getPhotoPosts(skip, top, filter));

            out.println(collection.toJsonString(res));
        } catch (Exception e) {
            out.println("Not found");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(collection.getPhotoPostDao().getConnection());
        }
    }
}
