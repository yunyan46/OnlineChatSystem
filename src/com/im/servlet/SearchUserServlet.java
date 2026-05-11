package com.im.servlet;

import com.im.dao.FriendDao;
import com.im.pojo.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            String key = req.getParameter("key");
            FriendDao dao = new FriendDao();
            List<User> list = dao.searchUser(key);
            req.setAttribute("list", list);
            req.getRequestDispatcher("searchResult.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}