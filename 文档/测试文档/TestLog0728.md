TestLog0728
===

目录
---

[TOC]

## 1. Login.do

### 1.1. 错误类型

Maven构建项目报错

### 1.2. 错误原因

`loginservlet.java`引用了两个不带Java后缀的文件`CountrydepartDao`和`ManagerDao`。

给上述两个文件加上.java后缀以后，依然报错。原因如下。

`CountrydepartDao` 引用了两个不存在的文件 `object.countrydepart`和 `object.student`

`ManagerDao` 引用了两个不存在的文件 `object.education_department`和`object.manager`。

### 1.3. 负责人

余文婧

新创建了一个分支test，暂时去除`loginservlet.java`文件，项目构建完成，并部署在`http://39.106.116.63:8080/courseDesign/`。

## 2. findStudentById.do

### 2.1. 错误类型1

访问`http://39.106.116.63:8080/courseDesign/findStudentById.do?id=1`时，显示

```java
java.lang.NullPointerException
 com.courseDesign.servlet.findStudentById.doPost(findStudentById.java:42)
 com.courseDesign.servlet.findStudentById.doGet(findStudentById.java:85)
 javax.servlet.http.HttpServlet.service(HttpServlet.java:634)
 javax.servlet.http.HttpServlet.service(HttpServlet.java:741)
 org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
```

参数读取失败。

### 2.2. 错误原因1

使用了特殊的方式读取请求内容

（其余接口也都存在此问题）

```java
// 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        HashMap map1 = JSONObject.parseObject(sb.toString(), HashMap.class);
        int id= Integer.parseInt(map1.get("id").toString());
```

虽然接口发送的数据格式为JSON，但能够被发送端自动适配，通过一般方式读取即可

```java
int id= Integer.parseInt(request.getParameter("id").toString());
```

### 2.3. 错误类型2

修改数据读取方式后，报类型错误

```java
java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.math.BigInteger (java.lang.Long and java.math.BigInteger are in module java.base of loader 'bootstrap')
 at com.courseDesign.servlet.findStudentById.doPost(findStudentById.java:53)
 at com.courseDesign.servlet.findStudentById.doGet(findStudentById.java:85)
```

### 2.4. 错误原因2

类型错误

```java
student.setTestid((BigInteger) map.get("testid"));
```

数据库中student表中testid字段为BIGINT(20)。

MySQL的bigint类型，对应到Java的Long类型。如果勾选了unsigned无符号，则会映射为BigInteger类型。

### 2.5. 负责人

闫开元

## 3. approveEnrolledStudent

### 3.1. 错误类型1

请求参数读取失败，同`findStudentById.do`中的错误类型1。

（其余接口也都存在此问题）

### 3.2. 错误类型2

接口理解错误。

### 3.3. 错误原因2

```java
String isapproved=(map1.get("is_approved").toString());
        if(isapproved.equals("录取"))
            is_approved=1;
        else if(isapproved.equals("退档"))
            is_approved=-1;
```

参考接口文档`https://app.swaggerhub.com/apis-docs/CurrenWong/CourseDesign/0.0.1#/UniversityEnrollStudent`

`isApproved`表示提档以后是否被录取，0未录取（即待定），1录取，-1退档。在数据库中为tinyint(4)类型，按照数字(0/1/-1)判定即可，不是按照中文判定。

### 3.4. 负责人

龚美
