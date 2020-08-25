package sources;

import static org.easymock.EasyMock.expect;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courseDesign.dao.BaseDao;
import com.courseDesign.servlet.submitPlan;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSubmitPlan extends EasyMockSupport {
    private submitPlan servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // private WebApplicationContext context;
    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new submitPlan();
        // 创建字符输出流
        out = new StringWriter();
        writer = new PrintWriter(out);
        // 创建mock对象
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        // 初始化
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
    }

    // 测试方法执行后
    @After
    public void tearDown() {
        // 验证所有mock都被使用
        verifyAll();
    }

    @Test
    public void testValidInput() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("planId", "8");
        jsonObj.put("year", "2020");
        jsonObj.put("regionId", "1");
        jsonObj.put("classId", "1");
        jsonObj.put("universityId", "1");
        jsonObj.put("number", "10");
        jsonObj.put("isApproved", "0");
        jsonObj.put("approvedBy", "");
        // 设置参数
        expect(request.getParameter("planId")).andReturn(jsonObj.getString("planId"));
        expect(request.getParameter("year")).andReturn(jsonObj.getString("year"));
        expect(request.getParameter("regionId")).andReturn(jsonObj.getString("regionId"));
        expect(request.getParameter("classId")).andReturn(jsonObj.getString("classId"));
        expect(request.getParameter("universityId")).andReturn(jsonObj.getString("universityId"));
        expect(request.getParameter("number")).andReturn(jsonObj.getString("number"));
        expect(request.getParameter("isApproved")).andReturn(jsonObj.getString("isApproved"));
        expect(request.getParameter("approvedBy")).andReturn(jsonObj.getString("approvedBy"));
        // 设置返回参数
        try {
            expect(response.getWriter()).andReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 切换到replay模式
        replayAll();
        // 发送post请求
        try {
            servlet.doPost(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        // 输出结果
        System.out.println("TestSubmitPlan Input: " + jsonObj.toJSONString());
        // 删除插入的数据
        String sql = "delete from dev.plan WHERE planid = ?;";
        BaseDao.executeUpdate(sql, 8);
        System.out.println("TestSubmitPlan Output: " + "{StatusCode: 200}");
    }
}
