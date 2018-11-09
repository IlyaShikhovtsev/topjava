<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="meal.title"/></h3>

        <form id="filterForm">
            <dl>
                <dt><spring:message code="meal.startDate"/>:</dt>
                <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="meal.endDate"/>:</dt>
                <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="meal.startTime"/>:</dt>
                <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
            </dl>
            <dl>
                <dt><spring:message code="meal.endTime"/>:</dt>
                <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
            </dl>
        </form>
        <button onclick="filter()"><span class="glyphicon glyphicon-filter" style="color: #2E6E9E"></span></button>
        <button onclick="clearFilters()"><span class="glyphicon glyphicon-scissors" style="color: #2E6E9E"></span></button>

        <hr>
        <button onclick="add()"><span class="glyphicon glyphicon-plus" style="color: red"></span></button>
        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                <tr id="${meal.id}" class="${meal.exceed ? 'exceeded' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals/update?id=${meal.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                    <td><a class="delete"><span class="glyphicon glyphicon-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<div class="modal fade" id="editRow" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel"><spring:message code="meal.add"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" name="id" value="${meal.id}">
                    <dl>
                        <dt><spring:message code="meal.dateTime"/>:</dt>
                        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meal.description"/>:</dt>
                        <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
                    </dl>
                    <dl>
                        <dt><spring:message code="meal.calories"/>:</dt>
                        <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
                    </dl>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
                <button type="submit" form="detailsForm" class="btn btn-primary"><spring:message
                        code="common.save"/></button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>