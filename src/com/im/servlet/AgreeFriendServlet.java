package com.im.servlet;

import com.im.dao.FriendDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/agree")
public class AgreeFriendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int from = Integer.parseInt(req.getParameter("from"));
            int to = Integer.parseInt(req.getParameter("to"));
            FriendDao dao = new FriendDao();
            dao.agree(id, from, to);
            resp.sendRedirect("mainInit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}