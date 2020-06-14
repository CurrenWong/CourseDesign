package com.courseDesign.dao;

import com.courseDesign.javabean.student;
import com.courseDesign.javabean.volunteer;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao extends BaseDao {

    public student login(String username, String password) {
        String sql = "select * from student where username=  '" + username + "' and password='" + password + "' ";
        student st = null;
        try {
            Connection coon = DatabaseLink.getConnection();
            PreparedStatement pstmt = coon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                st = new student();
                st.setName(rs.getString("username"));
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
    public student searchStudent(int i){
        student student=new student();
        String sql= "select * from student where id=?";
        try {
            Connection connection=DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt("id"));
                student.setTestid((BigInteger) rs.getObject("testid"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setRegionid(rs.getInt("regionid"));
                student.setTotal_score(rs.getInt("total_score"));
                student.setRank(rs.getInt("rank"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}


