<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Event Page</title>

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

<a href="/">Back to main menu</a>

<br/>
<br/>

<h1>Event List</h1>


<br/>
<br/>

<c:if test="${!empty listEvent}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Event Name</th>
            <th width="120">Mobile phone</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listEvent}" var="event">
            <tr>
                <td>${event.id}</td>
                <td><a href="<c:url value='/eventsdata/${event.id}' />" target="_blank">${event.eventName}</a></td>
                <td>${event.mobilePhone}</td>
                <td><a href="<c:url value='/event/update/${event.id}' />">Edit</a></td>
                <td><a href="<c:url value='/event/remove/${event.id}' />">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add event</h1>

<c:url var="addAction" value="/event/add"/>

<form:form action="${addAction}" commandName="event">
    <table>
        <c:if test="${!empty event.eventName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="eventName">
                    <spring:message text="Event Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="eventName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="mobilePhone">
                    <spring:message text="Mobile phone"/>
                </form:label>
            </td>
            <td>
                <form:input path="mobilePhone"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty event.eventName}">
                    <input type="submit"
                           value="<spring:message text="Edit event"/>"/>
                </c:if>
                <c:if test="${empty event.eventName}">
                    <input type="submit"
                           value="<spring:message text="Add event"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>