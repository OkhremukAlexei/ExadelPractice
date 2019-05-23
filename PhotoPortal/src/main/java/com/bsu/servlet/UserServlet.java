package com.bsu.servlet;

import com.bsu.entity.User;
import com.bsu.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserServiceImpl users = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = users.loginUser(login, password);
        if(user != null){
            response.getOutputStream().println(user.toString());
        }else {
            response.getOutputStream().println("Wrong data");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);
        if(users.registerUser(user)){
            response.getOutputStream().println("User has been successfully created");
        }else {
            response.getOutputStream().println("This user already exist");
        }
    }
}
