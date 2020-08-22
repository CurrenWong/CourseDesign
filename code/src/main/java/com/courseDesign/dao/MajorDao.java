package com.courseDesign.dao;

import com.courseDesign.javabean.major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MajorDao {
    public ArrayList<major> searchMajor(int i){
        ArrayList<major> majors=new ArrayList<major>();
        String sql = "select * from major where classid=?";
        Connection connection = null;
        try {
            connection=DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                major major=new major();
                major.setClassId(rs.getInt(1));
                major.setRegionId(rs.getInt(2));
                major.setNmajor(rs.getString(3));
                major.setNclass(rs.getString(4));
                major.setKind(rs.getInt(5));
                majors.add(major);
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
        return majors;
    }

    public String searchClassname(int i){
        String s="";
        String sql= "select nclass from major where classid=?";
        try {
            Connection connection=DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                s=rs.getNString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}
