<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    
    
    
</head>
<body>
<h3>Kharitonov presents</h3>

<br/>
<security:authorize access="hasRole('ROLE_ADMIN')">
<a href="<c:url value="/user"/>" >Users list</a>
<br/>

<br/>
<a href="<c:url value="/event"/>" >Event list</a>
<br/>

<br/>
<a href="<c:url value="/news"/>" >News list</a>
<br/>

<br/>
<a href="<c:url value="/usereventlist"/>" >Users event list</a>
<br/>
</security:authorize>
</body>
</html>
