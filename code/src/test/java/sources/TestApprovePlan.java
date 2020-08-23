package sources;

import static org.easymock.EasyMock.expect;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.courseDesign.dao.BaseDao;
import com.courseDesign.servlet.approvePlan;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestApprovePlan extends EasyMockSupport {
    private approvePlan servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // private WebApplicationContext context;
    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new approvePlan();
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
        jsonObj.put("planIdArray", "[1, 2");
        // 设置参数
        expect(request.getParameter("planIdArray")).andReturn(jsonObj.getString("planIdArray"));
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
        System.out.println("TestApprovePlan Input: " + jsonObj.getString("planIdArray"));
        System.out.println("TestApprovePlan Output: " + "{\"StatusCode\":\"200\"}");
        // 恢复修改的数据
        String sql = "update dev.plan set is_approved=0 WHERE planid = ? OR planid = ?;";
        BaseDao.executeUpdate(sql, 1, 2);
    }

    @Test
    public void testInvalidInput() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("planIdArray", "[100, 200");
        // 设置参数
        expect(request.getParameter("planIdArray")).andReturn(jsonObj.getString("planIdArray"));
        // 设置返回参数
        try {
            response.sendError(402, "提交失败，请刷新后重试");
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
        System.out.println("TestApprovePlan Input: " + jsonObj.getString("planIdArray"));
        System.out.println("TestApprovePlan Output: " + "{\"StatusCode\":\"402\"}");
    }
}