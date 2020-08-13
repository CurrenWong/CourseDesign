TestLog0813
===

目录
---

[TOC]

## 1. findStudentById

### 1.1. 错误类型1

输出错误

```java
-------------------------------------------------------------------------------
Test set: sources.TestFindStudentById
-------------------------------------------------------------------------------
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.218 s <<< FAILURE! - in sources.TestFindStudentById
sources.TestFindStudentById.testValidInput  Time elapsed: 0.102 s  <<< FAILURE!
java.lang.AssertionError: expected:<120> but was:<0>
 at sources.TestFindStudentById.testValidInput(TestFindStudentById.java:99)
```

### 1.2. 错误原因1

`StudentDao.java`中的`searchStudent`方法缺少了语文、数学成绩。

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

未处理异常值（其他接口也存在此错误）

### 1.4. 错误修改2

若学生不存在，需要返回错误状态码和错误信息，具体参考接口文档。

已经做了如下修改，测试通过。

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
    // 若学生不存在，返回状态码402
    response.sendError(402, "id输入错误");
}
```

### 1.5. 负责人

闫开元

## 2. findStudentByTestid

### 2.1. 错误类型1

数据类型错误，`testid`为`Long`类型，使用`Integer.parseInt`会报类型错误。

```java

java.lang.NumberFormatException: For input string: "19875426874625"
    at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
    at java.base/java.lang.Integer.parseInt(Integer.java:652)
    at java.base/java.lang.Integer.parseInt(Integer.java:770)
    at com.courseDesign.servlet.findStudentByTestid.doPost(findStudentByTestid.java:36)
    at sources.TestFindStudentByTestid.testValidInput(TestFindStudentByTestid.java:83)
```

### 2.2. 错误原因1

使用了`int`类型来转化14位数字。

```java
int testid= Integer.parseInt(request.getParameter("testId").toString());
```

### 2.3. 错误类型2

未处理异常值。

```java
java.lang.AssertionError:
  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(402 (int), "姓名或准考证号输入错误"): expected: 1, actual: 0
```

### 2.4. 错误原因2

输入不存在的`testid`时没有返回错误码和错误信息。

参考`findStudentById.java`进行修改。

### 2.5. 错误类型3

未处理姓名与准考证号不匹配的值。

```java
java.lang.AssertionError:
  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(402 (int), "姓名或准考证号输入错误"): expected: 1, actual: 0
```

### 2.6. 错误原因3

输入不匹配的`testid`时没有返回错误码和错误信息。

参考`findStudentById.java`进行修改。

### 2.7. 负责人

闫开元

## 3. findEnrolledStudent

### 3.1. 错误类型1

未处理异常值。

```java
java.lang.AssertionError:
  Unexpected method call HttpServletResponse.getWriter():
    HttpServletResponse.sendError(403 (int), "访问错误，请刷新后重试"): expected: 1, actual: 0
```

### 3.2. 错误原因1

输入不存在的`universityId`时没有返回错误码和错误信息。

参考`findStudentById.java`进行修改。

### 3.3. 负责人

闫开元

## 4. findEnrollmentResultById

### 4.1. 错误类型1

缺少返回值

```java
TestFindEnrollmentResultById Input: {"studentid":"1"}
TestFindEnrollmentResultById Output: {"studentid":1,"universityid":1,"year":"2020-01-01","class_id":5,"is_approved":1,"id":1,"type":"统招"}

java.lang.AssertionError:
Expected :1
Actual   :null
<Click to see difference></Click>

    at org.junit.Assert.fail(Assert.java:88)
    at org.junit.Assert.failNotEquals(Assert.java:743)
    at org.junit.Assert.assertEquals(Assert.java:118)
    at org.junit.Assert.assertEquals(Assert.java:144)
    at sources.TestFindEnrollmentResultById.testValidInput(TestFindEnrollmentResultById.java:95)
```

### 4.2. 错误原因1

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

### 4.3. 错误类型2

数据格式错误。

年份在数据库中存储为`2020`，实际输出为`"2020-01-01"`。

### 4.4. 错误原因2

数据库里的`year`类型，按照`int`类型读取即可，不需要转为`Date`

```java
ues.setYear((Date) map.get("year"));
```

### 4.5. 负责人

龚美
