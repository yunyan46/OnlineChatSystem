package com.im.dao;

import com.im.pojo.FriendRequest;
import com.im.pojo.User;
import com.im.utils.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {

    // 获取好友列表
    public List<User> getMyFriends(int uid) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT u.* FROM friend f JOIN sys_user u ON f.friend_id = u.id WHERE f.user_id = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNickname(rs.getString("nickname"));
                u.setSignature(rs.getString("signature"));
                u.setAvatar(rs.getString("avatar"));
                u.setStatus(rs.getInt("status"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 搜索用户
    public List<User> searchUser(String keyword) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM sys_user WHERE account LIKE ? OR nickname LIKE ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setAccount(rs.getString("account"));
                u.setNickname(rs.getString("nickname"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 发送好友申请
    public void sendRequest(int from, int to, String msg) {
        String sql = "INSERT INTO friend_request(from_uid, to_uid, message) VALUES (?,?,?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, from);
            ps.setInt(2, to);
            ps.setString(3, msg);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取好友申请
    public List<FriendRequest> getRequests(int toUid) {
        List<FriendRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM friend_request WHERE to_uid = ? AND status = 0";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, toUid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FriendRequest r = new FriendRequest();
                r.setId(rs.getInt("id"));
                r.setFromUid(rs.getInt("from_uid"));
                r.setMessage(rs.getString("message"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 同意申请
    public void agree(int id, int from, int to) {
        String sql1 = "UPDATE friend_request SET status=1 WHERE id=?";
        String sql2 = "INSERT INTO friend(user_id, friend_id) VALUES (?,?)";
        String sql3 = "INSERT INTO friend(user_id, friend_id) VALUES (?,?)";
        try (Connection conn = JdbcUtil.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sql1);
                 PreparedStatement ps2 = conn.prepareStatement(sql2);
                 PreparedStatement ps3 = conn.prepareStatement(sql3)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps2.setInt(1, from);
                ps2.setInt(2, to);
                ps2.executeUpdate();

                ps3.setInt(1, to);
                ps3.setInt(2, from);
                ps3.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}