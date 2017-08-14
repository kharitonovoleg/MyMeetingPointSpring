<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="/user/add" method="post">
    <input name="username" type="text" value="username">
    <input name="firstName" type="text" value="firstName">
    <input name="secondName" type="text" value="secondName">
    <input name="password" type="password" value="password">
    <input name="email" type="email" value="email">

    <select name="events[0].id">
        <c:forEach items="${listEvent}" var="event" varStatus="status">
            <option value="${event.id}">
                <c:out value="${event.eventName}"></c:out>
            </option>
        </c:forEach>
    </select>

    <input type="submit" value="ADD">
</form>

<form action="/event/add" method="post">
    <input name="eventName" type="text" value="eventName">
    <input name="mobilePhone" type="text" value="mobilePhone">

    <select name="users[0].id">
        <c:forEach items="${listUser}" var="user" varStatus="status">
            <option value="${user.id}">
                <c:out value="${user.firstName}"></c:out>
            </option>
        </c:forEach>
    </select>

    <input type="submit" value="ADD">
</form>

</body>
</html>
