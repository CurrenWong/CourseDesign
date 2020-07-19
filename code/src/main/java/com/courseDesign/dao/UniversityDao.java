package com.courseDesign.dao;

import com.courseDesign.javabean.education_department;
import com.courseDesign.javabean.major;
import com.courseDesign.javabean.volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UniversityDao extends BaseDao {
        public education_department login(String username, String password) {
            String sql = "select * from university where username=  '" + username + "' and password='" + password + "' ";
            education_department st = null;
            try {
                Connection coon = DatabaseLink.getConnection();
                PreparedStatement pstmt = coon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    st = new education_department();
                    st.setUsername(rs.getString("username"));
                    st.setPassword(rs.getString("password"));
                }
                rs.close();
                pstmt.close();
                coon.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return st;
        }

        public ArrayList<volunteer> searchVolunteer(int i){
            ArrayList<volunteer> volunteers=new ArrayList<volunteer>();

            String sql= "select * from volunteer where universityid=?";
            try {
                Connection connection=DatabaseLink.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,i);
                ResultSet rs=pstmt.executeQuery();
                while (rs.next()){
                    volunteer volunteer=new volunteer();
                    volunteer.setVolunteerid(rs.getInt("volunteerid"));
                    volunteer.setVolunteerno(rs.getInt("volunteerno"));
                    volunteer.setBatch(rs.getInt("batch"));
                    volunteer.setIs_adjust(rs.getInt("is_adjust"));
                    volunteer.setStudentid(rs.getInt("studentid"));
                    volunteer.setUniversityid(rs.getInt("universityid"));
                    volunteer.setClassid(rs.getInt("classid"));
                    volunteers.add(volunteer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return volunteers;
        }

    public major searchMajor(int i){
        major major=new major();
        String sql= "select * from major where classid=?";
        try {
            Connection connection=DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                major.setClassid(rs.getInt(1));
                major.setRegioned(rs.getInt(2));
                major.setNmajor(rs.getString(3));
                major.setNclass(rs.getString(4));
                major.setKind(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return major;
    }
}
