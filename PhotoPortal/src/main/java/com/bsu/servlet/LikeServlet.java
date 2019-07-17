package com.bsu.servlet;

import com.bsu.service.Impl.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeServlet extends HttpServlet {
    private PostServiceImpl collection = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String user = request.getParameter("user");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (collection.isHasUserLike(id, user)) {
                out.println(true);
                collection.removeLike(id, user);
            } else {
                out.println(false);
                collection.addLike(id, user);
            }
        }catch (Exception e) {
            response.getOutputStream().print("Error");
        }
    }
}
