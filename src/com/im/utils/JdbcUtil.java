package com.im.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {

    // 👉 这里必须改成你自己的 MySQL 账号密码！！！
    private static final String URL = "jdbc:mysql://localhost:3306/im_platform?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "4646";

    // 加载驱动（固定写法）
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ 驱动加载成功！");
        } catch (Exception e) {
            System.out.println("❌ 驱动加载失败！");
            e.printStackTrace();
        }
    }

    // 获取连接（绝对不会返回 null）
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ 数据库连接成功！");
            return conn;
        } catch (Exception e) {
            System.out.println("❌ 数据库连接失败！");
            e.printStackTrace();
            return null;
        }
    }

    // 关闭资源
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        close(conn, pstmt, null);
    }
}