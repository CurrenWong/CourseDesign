TestLog0825
===

目录
---

[TOC]

## 1. 已测试通过的接口

### 1.1. findEnrolledStudent

```cpp
TestFindEnrolledStudent Input: {"universityId":"1"}
TestFindEnrolledStudent Output: [{"majors":[{"nclass":"经济与贸易类","classId":5,"regionId":5,"kind":1,"batch":1,"nmajor":"国际经济与贸易、金融学、税收学、管理科学"}],"gender":"男","regionId":5,"name":"王一","rank":200,"testId":19875426874625,"id":1,"totalScore":698},{"majors":[{"nclass":"经济与贸易类","classId":5,"regionId":6,"kind":1,"batch":1,"nmajor":"国际经济与贸易、金融学、税收学、管理科学"}],"gender":"男","regionId":6,"name":"王琦","rank":8000,"testId":19856423581658,"id":7,"totalScore":542}]
TestFindEnrolledStudent Input: {"universityId":"0"}
TestFindEnrolledStudent Output: {"StatusCode":"403"}
```

负责人：闫开元

### 1.2. findStudentById

```cpp
[INFO] Running sources.TestFindStudentById
TestFindStudentById Input: {"id":"1"}
TestFindStudentById Output: {"gender":"男","englishScore":138,"regionId":5,"chinesrScore":120,"kind":2,"name":"王一","rank":200,"testId":19875426874625,"mathScore":142,"id":1,"compScore":298,"totalScore":698}
TestFindStudentById Input: {"id":"0"}
TestFindStudentById Output: {"StatusCode":"402"}
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.153 s - in sources.TestFindStudentById
```

负责人：闫开元

### 1.3. findStudentByTestid

```cpp
[INFO] Running sources.TestFindStudentByTestid
TestFindStudentByTestid Input: {"name":"王一", "testId":"19875426874625"}
TestFindStudentByTestid Output: {"gender":"男","englishScore":138,"regionId":5,"chinesrScore":120,"kind":2,"name":"王一","rank":200,"testId":19875426874625,"mathScore":142,"id":1,"compScore":298,"totalScore":698}
TestFindStudentByTestid Input: {"name":"王一", "testId":"0"}
TestFindStudentByTestid Output: {"StatusCode":"402"}
TestFindStudentByTestid Input: {"name":"王一", "testId":"12345824562584"}
TestFindStudentByTestid Output: {"StatusCode":"402"}
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.281 s - in sources.TestFindStudentByTestid
```

负责人：闫开元

### 1.4. submitPlan

```cpp
TestSubmitPlan Input: {"universityId":"1","number":"10","classId":"1","year":"2020","regionId":"1","approvedBy":"","planId":"8","isApproved":"0"}
TestSubmitPlan Output: {StatusCode: 200}
```

负责人：余文婧

## 2. 未测试接口

1. login.java
2. enroll.java

## 3. apporveEnrolledStudent

### 3.1. testValidInput

#### 3.1.1. 输入字段读取错误

输入为`{studentId: 1}`

读取为，`int id= Integer.parseInt(request.getParameter("studentid"));`

```cpp
-------------------------------------------------------------------------------
Test set: sources.TestApporveEnrolledStudent
-------------------------------------------------------------------------------
Tests run: 4, Failures: 4, Errors: 0, Skipped: 0, Time elapsed: 0.001 s <<< FAILURE! - in sources.TestApporveEnrolledStudent
sources.TestApporveEnrolledStudent.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletRequest.getParameter("studentid"):
    HttpServletRequest.getParameter("studentId"): expected: 1, actual: 0
    at sources.TestApporveEnrolledStudent.testValidInput(TestApporveEnrolledStudent.java:75)

sources.TestApporveEnrolledStudent.testValidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("studentId"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletRequest.getParameter("studentid")
    at sources.TestApporveEnrolledStudent.tearDown(TestApporveEnrolledStudent.java:62)

sources.TestApporveEnrolledStudent.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletRequest.getParameter("studentid"):
    HttpServletRequest.getParameter("studentId"): expected: 1, actual: 0
    at sources.TestApporveEnrolledStudent.testInvalidInput(TestApporveEnrolledStudent.java:104)

sources.TestApporveEnrolledStudent.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletRequest.getParameter("studentId"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletRequest.getParameter("studentid")
    at sources.TestApporveEnrolledStudent.tearDown(TestApporveEnrolledStudent.java:62)
```

### 3.2. testInvalidInput

#### 3.2.1. 未做出错处理

访问不存在的学生id时，应该返回错误码`response.sendError(403, "提交错误，请刷新后重试");`，参考`findStudentById`进行修改。

### 3.3. 负责人-闫开元

## 4. approvePlan

### 4.1. testValidInput

#### 4.1.1. 类型转换错误

```cpp
java.lang.ClassCastException: java.util.ArrayList cannot be cast to com.courseDesign.javabean.plan
    at com.courseDesign.servlet.approvePlan.doPost(approvePlan.java:37)
```

`List`类型不能通过强制类型转换转为`plan`类型。

```cpp
plan plan0 = (plan) baseDao.executeQuery(sql0,planid);
```

### 4.2. testInvalidInput

#### 4.2.1. 验证方式错误

类型转换错误，以下错误处理的验证方式 `planid0 ==null` 也失效。

```cpp
if(planid0 ==null){
    System.out.println("没进行批量退选");
}else {
    PrintWriter out=response.getWriter();
    for (int i = 0; i < planid0.size(); i++) {
        planid = Integer.parseInt(planid0.getString(i));
        String sql0="select * from plan  where planid=?";
        plan plan0 = (plan) baseDao.executeQuery(sql0,planid);
        if(plan0==null){
            response.sendError(402, "提交失败，请刷新后重试");
        }
        else {
            String sql = "update plan set is_approved=1 where planid=?";
            baseDao.executeUpdate(sql, planid);
        }
    }

}
```

### 4.3. 负责人-余文婧

## 5. findEnrollmentResultById

### 5.1. testValidInput

#### 5.1.1. 缺少返回值

```cpp
-------------------------------------------------------------------------------
Test set: sources.TestFindEnrollmentResultById
-------------------------------------------------------------------------------
Tests run: 3, Failures: 3, Errors: 0, Skipped: 0, Time elapsed: 4.858 s <<< FAILURE! - in sources.TestFindEnrollmentResultById
sources.TestFindEnrollmentResultById.testValidInput  Time elapsed: 4.797 s  <<< FAILURE!
java.lang.AssertionError: expected:<1> but was:<null>
    at sources.TestFindEnrollmentResultById.testValidInput(TestFindEnrollmentResultById.java:91)
```

缺少接口文档中的返回值，且返回值key与期望输出不匹配。

[https://app.swaggerhub.com/apis-docs/CurrenWong/CourseDesign/0.0.2#/Student/post_findEnrollmentResultById_do](https://app.swaggerhub.com/apis-docs/CurrenWong/CourseDesign/0.0.2#/Student/post_findEnrollmentResultById_do)

期望输出为

```java
{
  "id": 1,
  "universityId": 1,
  "universityName": "上海理工大学",
  "studentId": 1,
  "studentName": "王一",
  "year": 2020,
  "type": "统招",
  "classId": 5,
  "className": "经济与贸易类",
  "isApproved": 0
}
```

实际输出为

```java
{
  "studentid":1,
  "universityid":1,
  "year":"2020-01-01",
  "class_id":5,
  "is_approved":1,
  "id":1,
  "type":"统招"
}
```

#### 5.1.2. 数据格式错误

年份在数据库中存储为`2020`，实际输出为`"2020-01-01"`。

数据库里的`year`类型，按照`int`类型读取即可，不需要转为`Date`

```java
ues.setYear((Date) map.get("year"));
```

### 5.2. testInvalidInput

```cpp

sources.TestFindEnrollmentResultById.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(403 (int), "无录取结果"): expected: 1, actual: 0
    at sources.TestFindEnrollmentResultById.testInvalidInput(TestFindEnrollmentResultById.java:118)

sources.TestFindEnrollmentResultById.testInvalidInput  Time elapsed: 0 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletResponse.sendError(403 (int), "无录取结果"): expected: 1, actual: 0
  Unexpected method calls:
    HttpServletResponse.getWriter()
    at sources.TestFindEnrollmentResultById.tearDown(TestFindEnrollmentResultById.java:60)
```

#### 5.2.1. 缺少错误处理

输入不匹配的`studentid`时没有返回错误码和错误信息。

参考`findStudentById.java`进行修改。

### 5.3. 负责人-龚美

## 6. rejectEnrolledStudent

### 6.1. testInvalidInput

输入不匹配的`studentId`时没有返回错误码和错误信息。

参考`findStudentById.java`进行修改。

```cpp
-------------------------------------------------------------------------------
Test set: sources.TestRejectEnrolledStudent
-------------------------------------------------------------------------------
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.425 s <<< FAILURE! - in sources.TestRejectEnrolledStudent
sources.TestRejectEnrolledStudent.testInvalidInput  Time elapsed: 0.152 s  <<< FAILURE!
java.lang.AssertionError:

  Expectation failure on verify:
    HttpServletResponse.sendError(403 (int), "提交错误，请刷新后重试"): expected: 1, actual: 0
    at sources.TestRejectEnrolledStudent.tearDown(TestRejectEnrolledStudent.java:62)
```

### 6.2. 负责人-龚美

## 7. rejectPlan

### 7.1. testValidInput

#### 7.1.1. 类型转换错误

同`approvePlan.java`

`List`类型不能通过强制类型转换转为`plan`类型。

```cpp
plan plan0 = (plan) baseDao.executeQuery(sql0,planid);
```

### 7.2. testInvalidInput

#### 7.2.1. 验证方式错误

同`approvePlan.java`

类型转换错误，以下错误处理的验证方式 `planid0 ==null` 也失效。

```cpp
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
            String sql = "update plan set is_approved=-1 where planid=?";
            baseDao.executeUpdate(sql, planid);
        }
    }

}
```

### 7.3. 负责人-余文婧

## 8. submitVolunteer

### 8.1. testValidInput

#### 8.1.1. 输入读取错误

未按照接口文件读取输入

文档输入为

```cpp
{
  "volunteerNo": 1,
  "batch": 1,
  "isAdjust": 1,
  "studentId": 1,
  "universityId": 1,
  "classId": 5,
  "type": "统招"
}
```

读取输入为

```cpp
// 读取请求内容
int volunteerid= Integer.parseInt(request.getParameter("volunteerid").toString());
int volunteerno=Integer.parseInt(request.getParameter("volunteerno").toString());
int batch=Integer.parseInt(request.getParameter("batch").toString());
int is_adjust=Integer.parseInt(request.getParameter("is_adjust").toString());
int studentid=Integer.parseInt(request.getParameter("studentid").toString());
int universityid=Integer.parseInt(request.getParameter("universityid").toString());
int classid=Integer.parseInt(request.getParameter("classid").toString());
String type=request.getParameter("type").toString();
```

`volunteerid`在插入时可以设置为`null`，使用`mysql`自增主键`id`。

### 8.2. 负责人-龚美

## 9. findApprovedStudent

### 9.1. testValidInput

```cpp
java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.alibaba.fastjson.JSONArray
    at sources.TestFindApprovedStudent.testValidInput(TestFindApprovedStudent.java:88)
```

#### 9.1.1. 文档错误

文档中原本标注的输入描述为学校编号，字段应为`universityId`，但是我错写为了`studentId`，因此引发了误解。

目前测试逻辑是通过学生id查询的，而不是通过学校id查询的，需要修改为和`findEnrolledStudent.java` 类似的功能，查找输入的`universityId`在录取表中 `is_approved` 字段为1的学生信息。

#### 9.1.2. 返回类型错误

```cpp
java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.alibaba.fastjson.JSONArray
    at sources.TestFindApprovedStudent.testValidInput(TestFindApprovedStudent.java:88)
```

返回类型应为JSONArray组成的字符串，目前返回的是JSONObject字符串。

### 9.2. 负责人-闫开元
