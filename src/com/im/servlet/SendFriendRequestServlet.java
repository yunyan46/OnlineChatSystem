package com.im.servlet;

import com.im.dao.FriendDao;
import com.im.pojo.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sendRequest")
public class SendFriendRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User me = (User) req.getSession().getAttribute("loginUser");
            int to = Integer.parseInt(req.getParameter("to"));
            FriendDao dao = new FriendDao();
            dao.sendRequest(me.getId(), to, "请求加为好友");
            resp.sendRedirect("mainInit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}