<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список пользователей</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Имя пользователя</th>
        <th scope="col">Логин</th>
        <th scope="col">Роль</th>
        <th scope="col">Редактирование</th>
        <th scope="col">Удаление</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
    <tr>
        <th><c:out value="${user.getId()}"/>
        </th>
        <th><c:out value="${user.getUsername()}"/>
        </th>
        <th><c:out value="${user.getLogin()}"/>
        </th>
        <th>
            <c:forEach var="role" items="${user.getRoles()}">
                <c:out value="${role}"/>,
            </c:forEach>
        </th>
        <th><a href="/admin/edit/<c:out value="${user.getId()}"/>">Редакт.</a></th>
        <th><a href="/admin/delete/<c:out value="${user.getId()}"/>">Удалить</a></th>
    </tr>
    </c:forEach>

    </tbody>
</table>
<a class="btn btn-primary" href="/admin/add">Добавить</a><br>
<a class="btn" href="/user">Пользователь</a><br>
<a class="" href="/logout">Выйти</a>


</body>
</html>