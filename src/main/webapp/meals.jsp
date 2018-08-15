<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<a href="meals?action=create">Add meal</a>

<table border="1">
    <tr>
        <td>Дата</td>
        <td>Калории</td>
        <td>Описание</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>

    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class=${meal.exceed ? "exceeded" : "normal"}>
            <td>${fn:formatDateTime(meal.dateTime)}</td>
            <td>${meal.calories}</td>
            <td>${meal.description}</td>
            <td><a href="meals?action=update&id=${meal.id}">Edit</a></td>
            <td><a href="meals?action=remove&id=${meal.id}">Remove</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>