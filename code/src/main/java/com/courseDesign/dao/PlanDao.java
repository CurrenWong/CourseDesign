package com.courseDesign.dao;

import com.courseDesign.javabean.plan;

import java.sql.*;
import java.util.ArrayList;

public class PlanDao extends BaseDao {
    public plan searchPlan(int i) {
        plan plan = new plan();
        Connection connection = DatabaseLink.getConnection();
        String sql = "select * from plan  where planid=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                
                plan.setPlanid(rs.getInt("planid"));
                Date date = rs.getDate("year");
                plan.setYear(date.getYear());
                plan.setRegionid(rs.getInt("regionid"));
                plan.setClassid(rs.getInt("classid"));
                plan.setUniversotyid(rs.getInt("universityid"));
                plan.setNumber(rs.getInt("number"));
                plan.setIs_approved(rs.getInt("is_approved"));
                plan.setApproved_by(rs.getString("approved_by"));

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
        return plan;
    }
    
    public ArrayList<plan> searchUniversityPlan(int id, int year){
        ArrayList<plan> planList=new ArrayList<plan>();
        Connection connection= DatabaseLink.getConnection();
        String sql= "select * from plan  where universityid=? and year=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) {
                plan plan = new plan();
                plan.setPlanid(rs.getInt("planid"));
                Date date = rs.getDate("year");
                plan.setYear(date.getYear() + 1900);
                plan.setRegionid(rs.getInt("regionid"));
                plan.setClassid(rs.getInt("classid"));
                plan.setUniversotyid(rs.getInt("universityid"));
                plan.setNumber(rs.getInt("number"));
                plan.setIs_approved(rs.getInt("is_approved"));
                plan.setApproved_by(rs.getString("approved_by"));
                planList.add(plan);
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
        return planList;
    }
}
