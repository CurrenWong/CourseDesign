package com.courseDesign.dao;

import com.courseDesign.javabean.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao  extends BaseDao{
    public manager login(String username,String password) {
        String sql = "select * from manager where username=  '" + username + "' and password='" + password + "' ";
        manager st = null;
        try {
            Connection coon = DatabaseLink.getConnection();
            PreparedStatement pstmt = coon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                st = new manager();
                st.setUsername(rs.getString("username"));
                st.setPassword(rs.getString("password"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return st;
    }
}


