package com.courseDesign.dao;



import com.courseDesign.javabean.education_department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountrydepartDao extends BaseDao{
    public education_department login(String username, String password) {
        String sql = "select * from education_department where username=  '" + username + "' and password='" + password + "' ";
        education_department st = null;
        Connection coon = null;
        try {
            coon = DatabaseLink.getConnection();
            PreparedStatement pstmt = coon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                st = new education_department();
                st.setUsername(rs.getString("username"));
                st.setPassword(rs.getString("password"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                coon.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return st;
    }
}

