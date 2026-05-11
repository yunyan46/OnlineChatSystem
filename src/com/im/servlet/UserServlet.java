package com.im.servlet;

import com.im.pojo.User;
import com.im.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");

        // 注册
        if("register".equals(method)){
            String account = request.getParameter("account");
            String pwd = request.getParameter("password");
            String nick = request.getParameter("nickname");

            if(!userService.checkAccount(account)){
                request.setAttribute("msg","账号格式错误：6-20位字母数字下划线");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
                return;
            }
            if(!userService.checkPwd(pwd)){
                request.setAttribute("msg","密码必须6-20位，包含字母+数字");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
                return;
            }
            if(userService.accountExist(account)){
                request.setAttribute("msg","该账号已被注册");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
                return;
            }

            boolean res = userService.register(account,pwd,nick);
            if(res){
                response.sendRedirect("login.jsp");
            }else{
                request.setAttribute("msg","注册失败，请重试");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }
        }

        // 登录
        if("login".equals(method)){
            String account = request.getParameter("account");
            String pwd = request.getParameter("password");
            User user = userService.login(account,pwd);
            if(user != null){
                // 存入会话
                request.getSession().setAttribute("loginUser",user);
                response.sendRedirect("main.jsp");
            }else{
                request.setAttribute("msg","账号或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }
}