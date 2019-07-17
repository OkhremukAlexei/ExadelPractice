package com.bsu.servlet;

import com.bsu.entity.PhotoPost;
import com.bsu.service.Impl.PostServiceImpl;
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
    private PostServiceImpl collection = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PhotoPost post = collection.getPhotoPost(id);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println(post.toString());
        } catch (NullPointerException e){
            response.getOutputStream().println("Not found\n");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            InputStream in = request.getInputStream();
            Scanner sc = new Scanner(in);
            String data = sc.next();
            Gson gson = new Gson();

            Map<String, Object> map = gson.fromJson(data, Map.class);
            int id = 0;
            String author = "";
            String description = "";
            String photoLink = "";
            ArrayList<String> hashtags = new ArrayList<>();
            String creationDate = PostServiceImpl.buildCurrentDate().toString();

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

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (collection.addPhotoPost(post)) {
                out.println("Post has been successfully added\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            response.getOutputStream().println("Invalid post\n");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (collection.removePhotoPost(id)) {
                out.println("Post has been successfully deleted\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            response.setStatus(-1);
        }
    }
}
