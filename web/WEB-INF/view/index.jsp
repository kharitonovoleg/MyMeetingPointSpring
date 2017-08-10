<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Index</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

</head>
<body>
<h3>My meeting point</h3>

<table class="tg">
    <tr>About user</tr><br/>
    <tr>
        <th width="80">ID</th>
        <th width="120">Username</th>
        <th width="120">First Name</th>
        <th width="120">Second Name</th>
        <th width="120">Email</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.firstName}</td>
        <td>${user.secondName}</td>
        <td>${user.email}</td>
    </tr>
</table>

<tr>Users events</tr><br/>
<c:if test="${!empty eventList}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Event Name</th>
            <th width="120">Mobile phone</th>
            <th width="80">Date</th>
            <th width="80">Start Time</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${eventList}" var="event">
            <tr>
                <td>${event.id}</td>
                <td><a href="<c:url value='/eventsdata/${event.id}' />" >${event.eventName}</a></td>
                <td>${event.mobilePhone}</td>
                <td>${event.date}</td>
                <td>${event.eventStartTime}</td>
                <td><a href="<c:url value='/myevent/update/${event.id}' />">Edit</a></td>
                <td><a href="<c:url value='/myevent/remove/${event.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>



<form action="/adduserevent">
    <input name="eventName">
    <input name="mobilePhone">
    <input type="submit" name="Add event">
</form>

<br/>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="<c:url value="/user"/>">Users list</a>
    <br/>

    <br/>
    <a href="<c:url value="/event"/>">Event list</a>
    <br/>

    <br/>
    <a href="<c:url value="/news"/>">News list</a>
    <br/>

    <br/>
    <a href="<c:url value="/usereventlist"/>">Users event list</a>
    <br/>
</security:authorize>
</body>
</html>
