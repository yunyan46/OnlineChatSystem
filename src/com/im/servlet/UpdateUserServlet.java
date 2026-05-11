package com.im.servlet;

import com.im.dao.UserDao;
import com.im.pojo.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            User loginUser = (User) req.getSession().getAttribute("loginUser");

            String avatar = req.getParameter("avatar");
            String nickname = req.getParameter("nickname");
            String signature = req.getParameter("signature");
            Integer gender = Integer.parseInt(req.getParameter("gender"));
            String birthday = req.getParameter("birthday");
            Integer age = Integer.parseInt(req.getParameter("age"));

            UserDao dao = new UserDao();
            dao.updateUser(loginUser.getId(), avatar, nickname, signature, gender, birthday, age);

            loginUser.setAvatar(avatar);
            loginUser.setNickname(nickname);
            loginUser.setSignature(signature);
            loginUser.setGender(gender);
            loginUser.setBirthday(birthday);
            loginUser.setAge(age);

            req.getSession().setAttribute("loginUser", loginUser);

            // 自动返回主页
            resp.sendRedirect("mainInit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}