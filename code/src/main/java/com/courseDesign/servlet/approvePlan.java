package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONArray;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.plan;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "approvePlan",urlPatterns = "/approvePlan.do")
public class approvePlan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
        // 读取请求内容
   
       JSONArray planid0 = (JSONArray) JSONArray.parse(request.getParameter("planid"));
       // plan plan=new plan();
        BaseDao baseDao=new BaseDao();
        int planid=0;
        if(planid0 ==null){
            System.out.println("没进行批量退选");
        }else {

            for (int i = 0; i < planid0.size(); i++) {
                planid = Integer.parseInt(planid0.getString(i));
                String sql0="select * from plan  where planid=?";
                plan plan0 = (plan) baseDao.executeQuery(sql0,planid);
                if(plan0==null){
                    response.sendError(403, "提交失败，请刷新后重试");
                }
                else {
                    String sql = "update plan set is_approved=1 where planid=?";
                    baseDao.executeUpdate(sql, planid);
                }
            }

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

