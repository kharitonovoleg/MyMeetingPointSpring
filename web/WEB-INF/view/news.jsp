<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>News Page</title>

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

<h1>News List</h1>


<br/>
<br/>

<c:if test="${!empty listNews}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Header</th>
            <th width="120">Text</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listNews}" var="news">
            <tr>
                <td>${news.id}</td>
                <td><a href="<c:url value='/news/newsdata/${news.id}' />" target="_blank">${news.header}</a></td>
                <td>${news.text}</td>
                <td><a href="<c:url value='/news/update/${news.id}' />">Edit</a></td>
                <td><a href="<c:url value='/news/remove/${news.id}' />">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add event</h1>

<c:url var="addAction" value="/news/add"/>

<form:form action="${addAction}" commandName="news">
    <table>
        <c:if test="${!empty news.header}">
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
                <form:label path="header">
                    <spring:message text="Header"/>
                </form:label>
            </td>
            <td>
                <form:input path="header"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="text">
                    <spring:message text="Text"/>
                </form:label>
            </td>
            <td>
                <form:input path="text"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty news.header}">
                    <input type="submit"
                           value="<spring:message text="Edit news"/>"/>
                </c:if>
                <c:if test="${empty news.header}">
                    <input type="submit"
                           value="<spring:message text="Add news"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>