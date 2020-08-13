package com.courseDesign.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
    public static void executeUpdate(String sql,Object...obj){
        Connection con = DatabaseLink.getConnection();//调用getConnection()方法连接数据库
        PreparedStatement ps=null;
        try {
            ps = con.prepareStatement(sql);//预处理
            for(int i=0;i<obj.length;i++){//预处理声明占位符
                ps.setObject(i+1, obj[i]);
            }
            ps.executeUpdate();//执行更新操作
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{

            DatabaseLink.close( con,ps,null);//调用close()方法关闭资源
        }
    }

    public static List<Map<String,Object>> executeQuery(String sql,Object...obj) {
        Connection con = DatabaseLink.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            //new 一个空的list集合用来存放查询结果
            List<Map<String, Object>> list = new ArrayList<>();
            //获取结果集的列数
            int count = rs.getMetaData().getColumnCount();
            //对结果集遍历每一条数据是一个Map集合，列是k,值是v
            while (rs.next()) {
                //一个空的map集合，用来存放每一行数据
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < count; i++) {
                    Object ob = rs.getObject(i + 1);//获取值
                    String key = rs.getMetaData().getColumnName(i + 1);//获取k即列名
                    map.put(key, ob);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DatabaseLink.close(con, ps, rs);
        }
        return null;
    }
}
