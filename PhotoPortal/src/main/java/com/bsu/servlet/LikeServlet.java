package com.bsu.servlet;

import com.bsu.service.Impl.ConnectionPoolImpl;
import com.bsu.service.Impl.DBPostServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DBPostServiceImpl collection = new DBPostServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String user = request.getParameter("user");

            if (collection.isHasUserLike(id, user)) {
                collection.removeLike(id, user);
                out.println(true);
            } else {
                collection.addLike(id, user);
                out.println(false);
            }
        }catch (Exception e) {
            out.print("Error");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    collection.getPhotoPostDao().getConnection());
        }
    }
}
