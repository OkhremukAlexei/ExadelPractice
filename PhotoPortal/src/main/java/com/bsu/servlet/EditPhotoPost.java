package com.bsu.servlet;

import com.bsu.service.Impl.ConnectionPoolImpl;
import com.bsu.service.Impl.DBPostServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class EditPhotoPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBPostServiceImpl collection = new DBPostServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (InputStream in = request.getInputStream();
             Scanner sc = new Scanner(in)) {

            String data = sc.next();
            Gson gson = new Gson();

            Map<String, Object> map = gson.fromJson(data, Map.class);
            int id = Integer.parseInt(map.get("id").toString());

            if (collection.editPhotoPost(id, map)) {
                out.println("Post has been successfully change\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            out.println("Invalid data\n");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    collection.getPhotoPostDao().getConnection());
        }
    }
}
