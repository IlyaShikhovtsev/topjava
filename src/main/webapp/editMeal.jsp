<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h3>Edit meal</h3>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
    <input type="number" value="${meal.id}" name="id" hidden="hidden">
    <input type="datetime-local" value="${meal.dateTime}" name="datetime">
    <input type="text" value="${meal.description}" name="description">
    <input type="number" value="${meal.calories}" name="calories">
    <input type="submit">
</form>
</body>
</html>
