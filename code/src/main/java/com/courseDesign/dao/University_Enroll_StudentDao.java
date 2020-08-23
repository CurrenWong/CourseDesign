package com.courseDesign.dao;

import com.courseDesign.javabean.plan;
import com.courseDesign.javabean.university_enroll_student;
import com.courseDesign.javabean.volunteer;
import com.courseDesign.object.StudentAndVolunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class University_Enroll_StudentDao {

    public ArrayList<StudentAndVolunteer> searchVolunteer(int i, String j) {
        ArrayList<StudentAndVolunteer> volunteers = new ArrayList<StudentAndVolunteer>();

        String sql = "select dev.student.regionid,dev.student.total_score,dev.volunteer.*  from dev.student join dev.volunteer"
                + "where dev.student.id=dev.volunteer.studentid and batch=? and type=?"
                + "order by dev.student.regionid,dev.student.total_score desc,dev.volunteer.studentid,dev.volunteer.volunteerno asc;";
        Connection connection = null;
        try {
            connection = DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, i);
            pstmt.setString(2, j);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentAndVolunteer volunteer = new StudentAndVolunteer();
                volunteer.setRegionid(rs.getInt("regionide"));
                volunteer.setTotal_score(rs.getInt("total_score"));
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return volunteers;
    }

    public ArrayList<plan> SearchPlan() {
        Connection connection = DatabaseLink.getConnection();
        ArrayList<plan> plans = new ArrayList<plan>();
        String sql = "select * from plan order by universityid,regionid";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                plan plan = new plan();
                plan.setPlanid(rs.getInt(1));
                plan.setYear(rs.getDate(2));
                plan.setRegionid(rs.getInt(3));
                plan.setClassid(rs.getInt(4));
                plan.setUniversotyid(rs.getInt(5));
                plan.setNumber(rs.getInt(6));
                plan.setIs_approved(rs.getInt(7));
                plan.setApproved_by(rs.getString(8));
                plans.add(plan);
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
        return plans;
    }

    public void insertUES(university_enroll_student student) {
        Connection connection = DatabaseLink.getConnection();
        String sql = "insert into university_enroll_student(studentid,universityid,year,type,class_id,is_approved) values (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, student.getStudentid());
            pstmt.setInt(2, student.getUniversityid());
            pstmt.setDate(3, student.getYear());
            pstmt.setString(4, student.getType());
            pstmt.setInt(5, student.getClass_id());
            pstmt.setInt(6, student.getIs_approved());
            pstmt.executeUpdate();
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
    }

}
