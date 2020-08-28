package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONArray;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.dao.PlanDao;
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
        
        // 读取请求内容
        JSONArray planIdArray = (JSONArray) JSONArray.parse(request.getParameter("planIdArray"));
        // plan plan=new plan();
        BaseDao baseDao=new BaseDao();
        PlanDao planDao=new PlanDao();
        int planid=0;
        if(planIdArray ==null){
            System.out.println("没进行批量退选");
        }else {
            PrintWriter out=response.getWriter();
            System.out.println("bbb");
            for (int i = 0; i < planIdArray.size(); i++) {
                System.out.println("aaa");
                planid = Integer.parseInt(planIdArray.getString(i));
                plan plan=planDao.searchPlan(planid);
<<<<<<< HEAD
                if("null".equals(String.valueOf(plan.getPlanid()))||"0".equals(String.valueOf(plan.getPlanid()))){
=======
                if("null".equals(String.valueOf(plan.getPlanid()))&&"0".equals(String.valueOf(plan.getPlanid()))){
>>>>>>> 74f3b2891b05da671c92d945537e5acad96f1c95
                    System.out.println("2222222222");
                    response.sendError(403, "提交失败，请刷新后重试");
                }
                else {
                    String sql = "update plan set is_approved=1 where planid=?";
                    baseDao.executeUpdate(sql, planid);
                    System.out.println("1111111111");
                }
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

