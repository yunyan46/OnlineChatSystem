package com.im.servlet;

import com.im.pojo.User;
import com.im.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String account = request.getParameter("account");
            String password = request.getParameter("password");

            UserService userService = new UserService();
            User loginUser = userService.login(account, password);

            if (loginUser != null) {
                // 登录成功：存会话 → 跳主页初始化
                request.getSession().setAttribute("loginUser", loginUser);
                // 重点：直接跳 mainInit 加载好友群聊，再进主页面
                response.sendRedirect("mainInit");
            } else {
                // 账号密码错误，返回登录页提示
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script>alert('账号或密码错误');history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}