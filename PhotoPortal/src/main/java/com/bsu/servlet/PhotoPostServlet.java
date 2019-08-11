package com.bsu.servlet;

import com.bsu.model.PhotoPost;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class PhotoPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBPostServiceImpl collection = new DBPostServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PhotoPost post = collection.getPhotoPost(id);
            out.println(post.toString());
        } catch (NullPointerException e){
            out.println("Not found\n");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    collection.getPhotoPostDao().getConnection());
        }

    }

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
            int id = 0;
            String author = "";
            String description = "";
            String photoLink = "";
            ArrayList<String> hashtags = new ArrayList<>();
            String creationDate = DBPostServiceImpl.buildCurrentDate().toString();

            for(Map.Entry<String, Object> pair : map.entrySet()) {
                switch (pair.getKey()) {
                    case "id":
                        id = Integer.parseInt(String.valueOf(pair.getValue()));
                        break;
                    case "author":
                        author = String.valueOf(pair.getValue());
                        break;
                    case "description":
                        description = String.valueOf(pair.getValue());
                        break;
                    case "photoLink":
                        photoLink = String.valueOf(pair.getValue());
                        break;
                    case "hashtags":
                        String s = pair.getValue().toString();
                        String temp = s.substring(1, s.length() - 1);
                        hashtags = new ArrayList<>(Arrays.asList(temp.split(",")));
                        break;
                }
            }

            PhotoPost post = new PhotoPost(id, author, description, photoLink, creationDate, new ArrayList<>(), hashtags);

            if (collection.addPhotoPost(post)) {
                out.println("Post has been successfully added\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            out.println("Invalid post\n");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    collection.getPhotoPostDao().getConnection());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBPostServiceImpl collection = new DBPostServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String data = request.getParameter("id");
            int id = Integer.parseInt(data);

            if (collection.removePhotoPost(id)) {
                out.println("Post has been successfully deleted\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            out.println("Error\n");
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    collection.getPhotoPostDao().getConnection());
        }
    }
}
