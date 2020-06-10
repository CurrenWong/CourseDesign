package com.courseDesign.servlet;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.javabean.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "findStudentByTestid",urlPatterns = "/findStudentByTestid.do")
public class findStudentByTestid extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        PrintWriter out=response.getWriter();
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        HashMap map1 = JSONObject.parseObject(sb.toString(), HashMap.class);
        int testid= Integer.parseInt(map1.get("testId").toString());
        String name=map1.get("name").toString();

        student student=new student();
        BaseDao baseDao=new BaseDao();
        List<Map<String,Object>> maps=new ArrayList<>();
        String sql="select * from student where name=? and testid=?";
        maps=baseDao.executeQuery(sql,name,testid);
        for(int i=0;i<maps.size();i++){
            Map<String, Object> map=new HashMap<String, Object>();
            map=maps.get(i);
            student.setId((Integer) map.get("id"));
            student.setTestid((BigInteger) map.get("testid"));
            student.setGender((String) map.get("gender"));
            student.setName((String) map.get("name"));
            student.setRegionid((Integer) map.get("regionid"));
            student.setTotal_score((Integer) map.get("total_score"));
            student.setRank((Integer) map.get("rank"));
            student.setChinese_score((Integer) map.get("chinese_score"));
            student.setMath_score((Integer) map.get("math_score"));
            student.setEnglish_score((Integer) map.get("english_score"));
            student.setComp_score((Integer) map.get("comp_score"));
            student.setKind((Integer) map.get("kind"));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",student.getId());
        jsonObject.put("testId",student.getTestid());
        jsonObject.put("name",student.getName());
        jsonObject.put("gender",student.getGender());
        jsonObject.put("regionId",student.getRegionid());
        jsonObject.put("totalScore",student.getTotal_score());
        jsonObject.put("rank",student.getRank());
        jsonObject.put("chinesrScore",student.getChinese_score());
        jsonObject.put("mathScore",student.getMath_score());
        jsonObject.put("englishScore",student.getEnglish_score());
        jsonObject.put("compScore",student.getComp_score());
        jsonObject.put("kind",student.getKind());

        out.print(jsonObject.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
