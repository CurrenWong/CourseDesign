package com.courseDesign.dao;

import com.courseDesign.javabean.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDao extends BaseDao {
    public plan searchPlan(int i){
        plan plan =new plan();
        Connection connection= DatabaseLink.getConnection();
        String sql= "select * from plan  where planid=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
               plan.setPlanid(rs.getInt("planid"));
                plan.setYear(rs.getInt("year"));
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
}
