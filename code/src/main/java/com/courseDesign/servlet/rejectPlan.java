package com.courseDesign.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.plan;


@WebServlet(name = "rejectPlan",urlPatterns = "/rejectPlan.do")
public class rejectPlan  extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
    
       
         JSONArray planIdArray = (JSONArray) JSONArray.parse(request.getParameter("planIdArray"));
        BaseDao baseDao=new BaseDao();
        int planid=0;
        if(planIdArray ==null){
            System.out.println("没进行批量退选");
        }else {
            for (int i = 0; i < planIdArray.size(); i++) {
                planid = Integer.parseInt(planIdArray.getString(i));
                    String sql = "update plan set is_approved=-1 where planid=?";
                    baseDao.executeUpdate(sql, planid);

            }

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


