package com.courseDesign.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.courseDesign.javabean.student;
import com.courseDesign.javabean.university_enroll_student;
import com.courseDesign.javabean.volunteer;

public class StudentDao extends BaseDao {

    public student login(String username, String password) {
        String sql = "select * from student where username=  '" + username + "' and password='" + password + "' ";
        student st = null;
        Connection coon = null;
        try {
            coon = DatabaseLink.getConnection();
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
    public student searchStudent(int i){
        student student=new student();
        Connection connection=DatabaseLink.getConnection();
        String sql= "select * from student where id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt("id"));
                student.setTestid(rs.getLong("testid"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setRegionid(rs.getInt("regionid"));
                student.setTotal_score(rs.getInt("total_score"));
                student.setRank(rs.getInt("rank"));
                student.setChinese_score(rs.getInt("chinese_score"));
                student.setMath_score(rs.getInt("math_score"));
                student.setEnglish_score(rs.getInt("english_score"));
                student.setComp_score(rs.getInt("comp_score"));
                student.setKind(rs.getInt("kind"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return student;
    }

    public student searchStudentbytestid(long i,String name){
        student student=new student();
        Connection connection=DatabaseLink.getConnection();
        String sql= "select * from student where testid=? and name=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1,i);
            pstmt.setString(2,name);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt("id"));
                student.setTestid(rs.getLong("testid"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setRegionid(rs.getInt("regionid"));
                student.setTotal_score(rs.getInt("total_score"));
                student.setRank(rs.getInt("rank"));
                student.setChinese_score(rs.getInt("chinese_score"));
                student.setMath_score(rs.getInt("math_score"));
                student.setEnglish_score(rs.getInt("english_score"));
                student.setComp_score(rs.getInt("comp_score"));
                student.setKind(rs.getInt("kind"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return student;
    }

    public ArrayList<university_enroll_student> searchUES(int i){
        Connection connection=DatabaseLink.getConnection();
        ArrayList<university_enroll_student> universityEnrollStudents=new ArrayList<university_enroll_student>();
        String sql= "select * from university_enroll_student where universityid=? and is_approve=1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                university_enroll_student universityEnrollStudent=new university_enroll_student();
                universityEnrollStudent.setId(rs.getInt(1));
                universityEnrollStudent.setUniversityid(rs.getInt(2));
                universityEnrollStudent.setStudentid(rs.getInt(3));
                universityEnrollStudent.setYear(rs.getInt(4));
                universityEnrollStudent.setType(rs.getString(5));
                universityEnrollStudent.setClass_id(rs.getInt(6));
                universityEnrollStudent.setIs_approved(rs.getInt(7));
                universityEnrollStudents.add(universityEnrollStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return  universityEnrollStudents;
    }

    public ArrayList<volunteer> searchVolunteer(int i,int j){
        ArrayList<volunteer> volunteers=new ArrayList<volunteer>();
        Connection connection=DatabaseLink.getConnection();
        String sql= "select * from volunteer where studentid=? and universityid=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            pstmt.setInt(2,j);
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
                volunteer.setType(rs.getNString("type"));
                volunteers.add(volunteer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return volunteers;
    }


}


