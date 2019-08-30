package com.bsu.servlet;


import com.bsu.exception.LoginException;
import com.bsu.exception.PasswordException;
import com.bsu.model.User;
import com.bsu.service.Impl.ConnectionPoolImpl;
import com.bsu.service.Impl.DBLoginServiceImpl;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DBLoginServiceImpl loginService = new DBLoginServiceImpl();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try (InputStream in = request.getInputStream();
             Scanner sc = new Scanner(in)) {

            String data = sc.next();
            Gson gson = new Gson();

            Map<String, String> map = gson.fromJson(data, Map.class);
            String user = null, password = null;

            for(Map.Entry<String, String> pair : map.entrySet()) {
                switch (pair.getKey()) {
                    case "user":
                        user = String.valueOf(pair.getValue());
                        break;
                    case "password":
                        byte decodeBytes[] = Base64.getDecoder().decode(pair.getValue());
                        password = new String(decodeBytes);
                        break;
                }
            }

            try {
                HttpSession session = request.getSession(true);
                User loginUser = loginService.loginUser(user, password);
                session.setAttribute("username", loginUser.getUserName());
                session.setAttribute("hash", loginUser.getPasswordHash());
            } catch (LoginException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (PasswordException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionPoolImpl.getPool().releaseConnection(
                    loginService.getUserDao().getConnection());
        }
    }
}
