package com.im.servlet;

import com.im.pojo.User;
import com.im.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 编码统一（过滤器也兜底，双重保险）
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");

            UserService userService = new UserService();

            // 校验格式
            if (!userService.checkAccount(account)) {
                printMsg(response, "账号格式不符合规则！6-20位，只能字母数字下划线");
                return;
            }
            if (!userService.checkPwd(password)) {
                printMsg(response, "密码格式不符合规则！必须包含字母和数字，6-20位");
                return;
            }
            if (!userService.checkNickname(nickname)) {
                printMsg(response, "昵称格式不符合规则！2-15位，不能含空格");
                return;
            }

            // 判断账号是否已存在
            if (userService.accountExist(account)) {
                printMsg(response, "该账号已被注册！");
                return;
            }

            // 执行注册
            boolean res = userService.register(account, password, nickname);
            if (res) {
                // 注册成功 → 跳转到登录页
                response.getWriter().write("<script>alert('注册成功，请登录');location.href='login.jsp';</script>");
            } else {
                printMsg(response, "注册失败，请重试");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 弹出提示并返回注册页
    private void printMsg(HttpServletResponse response, String msg) throws Exception {
        PrintWriter out = response.getWriter();
        out.write("<script>alert('" + msg + "');history.back();</script>");
    }
}