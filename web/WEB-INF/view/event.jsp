<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>

    <title>Event Page</title>

    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script>
    <script type="text/javascript"
            src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>


    <%--<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.11.1.min.js"></script>--%>
    <%--<script type="text/javascript" src="${contextPath}/resources/js/moment-with-locales.min.js"></script>--%>
    <%--<script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    <%--<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>--%>

    <%--<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">--%>

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


<%--<input type="text" class="form-control" id="datetimepicker2">--%>

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

        <div class="well">
            <div id="datetimepicker4" class="input-append">
                <input data-format="yyyy-MM-dd" type="text" name="date"></input>
                <span class="add-on">
      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
      </i>
    </span>
            </div>
        </div>


        <div class="well">
            <div id="datetimepicker3" class="input-append">
                <input data-format="hh:mm:ss" type="text" name="eventStartTime"></input>
                <span class="add-on">
      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
      </i>
    </span>
            </div>
        </div>


            <%--<tr>--%>
            <%--<td>--%>
            <%--<input name="date" type="date">--%>
            <%--</td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
            <%--<td>--%>
            <%--<input name="eventStartTime" type="text">--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<div class="form-group">--%>
            <%--<div class="input-group date" id="datetimepicker1">--%>
            <%--<input type="text" name="date" class="form-control"/>--%>
            <%--<span class="input-group-addon">--%>
            <%--<span class="glyphicon-calendar glyphicon"></span>--%>
            <%--</span>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</td>--%>
            <%--</tr>--%>

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

<script type="text/javascript">
    $(function () {
        $('#datetimepicker4').datetimepicker({
            pickTime: false
        });
    });
</script>

<script type="text/javascript">
    $(function () {
        $('#datetimepicker3').datetimepicker({
            pickDate: false
        });
    });
</script>

<%--<script type="text/javascript">--%>
<%--$(function () {--%>
<%--$('#datetimepicker1').datetimepicker({--%>
<%--language: 'eng',--%>
<%--minuteStepping: 10,--%>
<%--defaultDate: "09.01.2015",--%>
<%--daysOfWeekDisabled: [0, 6]--%>
<%--});--%>
<%--$('#datetimepicker2').datetimepicker({language: 'ru'});--%>
<%--});--%>
<%--</script>--%>

</body>
</html>