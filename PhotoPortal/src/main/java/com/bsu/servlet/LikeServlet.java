package com.bsu.servlet;

import com.bsu.service.Impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeServlet extends HttpServlet {
    private PostServiceImpl collection = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String user = request.getParameter("user");
        if (collection.isHasUserLike(id, user)) {
            collection.removeLike(id, user);
        } else {
            collection.addLike(id, user);
        }
    }
}
