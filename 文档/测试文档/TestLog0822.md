TestLog0822
===

目录
---

[TOC]

## 1. approvePlan

### 1.1. 错误类型1

输入读取错误

```java
-------------------------------------------------------------------------------
Test set: sources.TestApprovePlan
-------------------------------------------------------------------------------
Tests run: 4, Failures: 4, Errors: 0, Skipped: 0, Time elapsed: 0.003 s <<< FAILURE! - in sources.TestApprovePlan
sources.TestApprovePlan.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletRequest.getParameter("is_approved"):
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
 at sources.TestApprovePlan.testValidInput(TestApprovePlan.java:79)

sources.TestApprovePlan.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletRequest.getParameter("is_approved")
 at sources.TestApprovePlan.tearDown(TestApprovePlan.java:62)
```

### 1.2. 错误原因1

需求理解错误，输入为一个planId组成的数组`{planIdArray:[1, 2]}`，需要读取为`JSONArray`，然后将每个planId的 `is_approved` 字段置为1，。参照[接口文档](https://app.swaggerhub.com/apis-docs/CurrenWong/CourseDesign/0.0.2#/EducationDepartment/post_approvePlan_do)。

参考`rejectEnrolledStudent.java`进行修改。

```java
public student searchStudent(int i){
        student student=new student();
        String sql= "select * from student where id=?";
        try {
            Connection connection=DatabaseLink.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,i);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt("id"));
                student.setTestid(rs.getLong("testid"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setRegionid(rs.getInt("regionid"));
                student.setTotal_score(rs.getInt("total_score"));
                student.setRank(rs.getInt("rank"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
```

### 1.3. 错误类型2

```cpp
sources.TestApprovePlan.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(403 (int), "提交失败，请刷新后重试"): expected: 1, actual: 0
 at sources.TestApprovePlan.testInvalidInput(TestApprovePlan.java:106)

sources.TestApprovePlan.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
 at sources.TestApprovePlan.tearDown(TestApprovePlan.java:62)
```

### 1.4. 错误原因2

未处理异常值。如果提交的id不存在，如`{planIdArray:[100, 200]}`，应该返回状态码403和错误信息，`response.sendError(403, "提交失败，请刷新后重试");`。

参考`findStudentById.java`进行修改。

```java
// 若学生存在
if (!"null".equals(String.valueOf(student.getId())) && !"0".equals(String.valueOf(student.getId()))) {
    PrintWriter out = response.getWriter();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", student.getId());
    jsonObject.put("testId", student.getTestid());
    jsonObject.put("name", student.getName());
    jsonObject.put("gender", student.getGender());
    jsonObject.put("regionId", student.getRegionid());
    jsonObject.put("totalScore", student.getTotal_score());
    jsonObject.put("rank", student.getRank());
    jsonObject.put("chinesrScore", student.getChinese_score());
    jsonObject.put("mathScore", student.getMath_score());
    jsonObject.put("englishScore", student.getEnglish_score());
    jsonObject.put("compScore", student.getComp_score());
    jsonObject.put("kind", student.getKind());
    out.print(jsonObject.toString());
} else {
    // 若学生不存在，返回状态码403
    response.sendError(403, "id输入错误");
}
```

### 1.5. 负责人

余文婧

## 2. rejectPlan

### 2.1. 错误类型1

输入错误

```java
-------------------------------------------------------------------------------
Test set: sources.TestRejectPlan
-------------------------------------------------------------------------------
Tests run: 4, Failures: 4, Errors: 0, Skipped: 0, Time elapsed: 0.001 s <<< FAILURE! - in sources.TestRejectPlan
sources.TestRejectPlan.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletRequest.getParameter("is_approved"):
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
 at sources.TestRejectPlan.testValidInput(TestRejectPlan.java:79)

sources.TestRejectPlan.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletRequest.getParameter("is_approved")
 at sources.TestRejectPlan.tearDown(TestRejectPlan.java:62)
```

### 2.2. 错误原因1

需求理解错误，输入为一个planId组成的数组`{planIdArray:["1", "2"]}`，需要读取为`JSONArray`，然后将每个planId的 `is_approved` 字段置为0，。参照[接口文档](https://app.swaggerhub.com/apis-docs/CurrenWong/CourseDesign/0.0.2#/EducationDepartment/post_approvePlan_do)。

### 2.3. 错误类型2

未处理异常值。

```java
sources.TestRejectPlan.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(403 (int), "提交失败，请刷新后重试"): expected: 1, actual: 0
 at sources.TestRejectPlan.testInvalidInput(TestRejectPlan.java:103)

sources.TestRejectPlan.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("planIdArray"): expected: 1, actual: 0
 at sources.TestRejectPlan.tearDown(TestRejectPlan.java:62)
```

### 2.4. 错误原因2

未处理异常值。如果提交的id不存在，如`{planIdArray:["100", "200"]}`，应该返回状态码403和错误信息，`response.sendError(403, "提交失败，请刷新后重试");`。

参考`findStudentById.java`进行修改。

### 2.5. 负责人

余文婧

## 3. submitPlan

### 3.1. 错误类型1

读取字段名错误

```cpp
java.lang.AssertionError:
  Expectation failure on verify:
    HttpServletRequest.getParameter("regionId"): expected: 1, actual: 0
    HttpServletRequest.getParameter("classId"): expected: 1, actual: 0
    HttpServletRequest.getParameter("universityId"): expected: 1, actual: 0
    HttpServletRequest.getParameter("number"): expected: 1, actual: 0
    HttpServletRequest.getParameter("isApproved"): expected: 1, actual: 0
    HttpServletRequest.getParameter("approvedBy"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletRequest.getParameter("regionid")
```

### 3.2. 错误原因1

未按照接口文档字段名定义读取。接口文档传入的字段名和数据库中的不是完全一样的，数据库中使用下划线是因为mysql不区分大小写，而在java中应使用大小写命名。

输入为

```cpp
{
    "planId": 1,
    "year": 2020,
    "regionId": 1,
    "classId": 5,
    "universityId": 1,
    "number": 20,
    "isApproved": 0,
    "approvedBy": "李四"
  }
```

读取为

```cpp
int planid= Integer.parseInt(request.getParameter("planid").toString());
String year =request.getParameter("year").toString();
int regionid=Integer.parseInt(request.getParameter("regionid").toString());
int classid=Integer.parseInt(request.getParameter("classid").toString());
int universotyid=Integer.parseInt(request.getParameter("universotyid").toString());
int number=Integer.parseInt(request.getParameter("number").toString());
int is_approved=Integer.parseInt(request.getParameter("is_approved").toString());
String approved_by=request.getParameter("approved_by").toString();
```

### 3.3. 负责人

余文婧
