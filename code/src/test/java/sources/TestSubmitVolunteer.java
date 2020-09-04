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
import com.courseDesign.servlet.submitVolunteer;

import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSubmitVolunteer extends EasyMockSupport {
    private submitVolunteer servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    StringWriter out;
    PrintWriter writer;

    // private WebApplicationContext context;
    // 测试方法执行前
    @Before
    public void setUp() {
        // 创建Servlet
        servlet = new submitVolunteer();
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
        jsonObj.put("volunteerNo", 12);
        jsonObj.put("batch", 1);
        jsonObj.put("isAdjust", 1);
        jsonObj.put("studentId", 1);
        jsonObj.put("universityId", 1);
        jsonObj.put("classId", 5);
        jsonObj.put("type", "统招");
        // 设置参数
        expect(request.getParameter("volunteerNo")).andReturn(jsonObj.getString("volunteerNo"));
        expect(request.getParameter("batch")).andReturn(jsonObj.getString("batch"));
        expect(request.getParameter("isAdjust")).andReturn(jsonObj.getString("isAdjust"));
        expect(request.getParameter("studentId")).andReturn(jsonObj.getString("studentId"));
        expect(request.getParameter("universityId")).andReturn(jsonObj.getString("universityId"));
        expect(request.getParameter("classId")).andReturn(jsonObj.getString("classId"));
        expect(request.getParameter("type")).andReturn(jsonObj.getString("type"));
        expect(request.getParameter("classRank")).andReturn(jsonObj.getString("1"));
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
        System.out.println("TestsubmitVolunteer Input: " + jsonObj.toJSONString());
        // 删除插入的数据
        String sql = "delete from dev.plan WHERE volunteerNo = ?;";
        BaseDao.executeUpdate(sql, Integer.parseInt(jsonObj.getString("volunteerNo")));
    }
}
