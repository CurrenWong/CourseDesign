package com.courseDesign.dao;

import com.courseDesign.javabean.education_department;
import com.courseDesign.javabean.major;
import com.courseDesign.javabean.university;
import com.courseDesign.javabean.volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UniversityDao extends BaseDao {
    public university login(String username, String password) {
        String sql = "select * from university where username=  '" + username + "' and password='" + password + "' ";
        university st = null;
        Connection coon = null;
        try {
            coon = DatabaseLink.getConnection();
            PreparedStatement pstmt = coon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                st = new university();
                st.setUsername(rs.getString("username"));
                st.setPassword(rs.getString("password"));
            }
            rs.close();
            pstmt.close();
            coon.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 释放数据库连接
            try {
                coon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return st;
    }

    public ArrayList<volunteer> searchVolunteer(int i) {
        ArrayList<volunteer> volunteers = new ArrayList<volunteer>();

        String sql = "select * from volunteer where universityid=?";
        try {
            Connection connection = DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                volunteer volunteer = new volunteer();
                volunteer.setVolunteerid(rs.getInt("volunteerid"));
                volunteer.setVolunteerno(rs.getInt("volunteerno"));
                volunteer.setBatch(rs.getInt("batch"));
                volunteer.setIs_adjust(rs.getInt("is_adjust"));
                volunteer.setStudentid(rs.getInt("studentid"));
                volunteer.setUniversityid(rs.getInt("universityid"));
                volunteer.setClassid(rs.getInt("classid"));
                volunteer.setType(rs.getNString("type"));
                volunteers.add(volunteer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volunteers;
    }

}
