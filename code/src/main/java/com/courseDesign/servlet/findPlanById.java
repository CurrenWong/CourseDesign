package com.courseDesign.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.PlanDao;
import com.courseDesign.javabean.plan;

@WebServlet(name = "FindPlanById", urlPatterns = "/findPlanById.do")
public class findPlanById extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        int id = Integer.parseInt(request.getParameter("universityId").toString());
        int year = Integer.parseInt(request.getParameter("year").toString());

        PlanDao planDao = new PlanDao();

        ArrayList<plan> planList = planDao.searchUniversityPlan(id, year);

        // 若plan存在
        if (planList.size() > 0) {
            PrintWriter out = response.getWriter();
            JSONArray jsonArray = new JSONArray();
            for (plan planBuffer : planList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("planid", planBuffer.getPlanid());
                jsonObject.put("year", planBuffer.getYear());
                jsonObject.put("regionid", planBuffer.getRegionid());
                jsonObject.put("classid", planBuffer.getClassid());
                jsonObject.put("universityid", planBuffer.getUniversotyid());
                jsonObject.put("number", planBuffer.getNumber());
                jsonObject.put("is_approved", planBuffer.getIs_approved());
                jsonObject.put("approved_by", planBuffer.getApproved_by());
                jsonArray.add(jsonObject);
            }
            out.print(jsonArray.toString());
        } else {
            // 若不存在，返回状态码402
            response.sendError(402, "无查询结果");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
