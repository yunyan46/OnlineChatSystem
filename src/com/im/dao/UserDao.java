package com.im.dao;

import com.im.pojo.User;
import com.im.utils.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User findUserByAccount(String account) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from sys_user where account=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, account);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setAccount(rs.getString("account"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            System.out.println("=== 查询用户异常 ===");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return user;
    }

    public int addUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into sys_user(account,password,nickname) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getAccount());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNickname());

            System.out.println("=== 开始插入数据 ===");
            System.out.println("账号：" + user.getAccount());
            System.out.println("密码：" + user.getPassword());
            System.out.println("昵称：" + user.getNickname());

            rows = pstmt.executeUpdate();
            System.out.println("=== 插入成功，影响行数：" + rows + " ===");

        } catch (Exception e) {
            System.out.println("===== 数据库插入异常 =====");
            e.printStackTrace(); // 这里会打印真实错误
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return rows;
    }
    public void updateUser(Integer id, String avatar, String nickname,
                           String signature, Integer gender,
                           String birthday, Integer age) {
        Connection conn = JdbcUtil.getConnection();
        String sql = "UPDATE sys_user SET avatar=?,nickname=?,signature=?,gender=?,birthday=?,age=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, avatar);
            pstmt.setString(2, nickname);
            pstmt.setString(3, signature);
            pstmt.setInt(4, gender);
            pstmt.setString(5, birthday);
            pstmt.setInt(6, age);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
