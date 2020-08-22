package sources;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import com.courseDesign.dao.BaseDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.courseDesign.servlet.submitPlan;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
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
        // 设置参数
        expect(request.getParameter("planId")).andReturn("8");
        expect(request.getParameter("year")).andReturn("2020");
        expect(request.getParameter("regionId")).andReturn("1");
        expect(request.getParameter("classId")).andReturn("1");
        expect(request.getParameter("universityId")).andReturn("1");
        expect(request.getParameter("number")).andReturn("10");
        expect(request.getParameter("isApproved")).andReturn("0");
        expect(request.getParameter("approvedBy")).andReturn("");
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
        System.out.println("TestSubmitPlan Input: " + "{" + "\"planId\": 1," + "\"year\": 2020," + "\"regionId\": 1,"
                + "\"classId\": 5," + "\"universityId\": 1," + "\"number\": 20," + "\"isApproved\": 0,"
                + "\"approvedBy\": \"李四\"" + "}\"");
        System.out.println("TestSubmitPlan Output: " + "{\"StatusCode\":\"200\"}");
        // 删除插入的数据
        BaseDao baseDao = new BaseDao();
        String sql = "delete from dev.plan WHERE planid = ?;";
        baseDao.executeUpdate(sql, 8);
    }
}
