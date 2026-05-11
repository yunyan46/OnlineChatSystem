package com.im.servlet;

import com.im.pojo.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mainInit")
public class MainInitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            // 转发
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}