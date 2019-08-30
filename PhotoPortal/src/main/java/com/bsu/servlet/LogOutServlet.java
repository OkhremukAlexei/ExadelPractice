package com.bsu.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // DBLoginServiceImpl loginService = new DBLoginServiceImpl();
        HttpSession session = request.getSession();
        if(session != null) {
            session.invalidate();
        }
    }
}
