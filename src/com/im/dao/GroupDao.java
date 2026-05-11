package com.im.dao;

import com.im.pojo.GroupChat;
import com.im.utils.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    // 根据用户ID 查询加入的所有群
    public List<GroupChat> getGroupList(Integer userId){
        List<GroupChat> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT g.* FROM group_member m " +
                "JOIN group_chat g ON m.group_id = g.id " +
                "WHERE m.user_id = ?";
        try{
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                GroupChat group = new GroupChat();
                group.setId(rs.getInt("id"));
                group.setGroupName(rs.getString("group_name"));
                group.setGroupAvatar(rs.getString("group_avatar"));
                list.add(group);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try { if(rs!=null)rs.close(); }catch (Exception e){}
            try { if(pstmt!=null)pstmt.close(); }catch (Exception e){}
            try { if(conn!=null)conn.close(); }catch (Exception e){}
        }
        return list;
    }
}