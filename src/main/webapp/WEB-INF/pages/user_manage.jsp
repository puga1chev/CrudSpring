<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление/редатирование пользователя</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<form:form action="${action}" method="post" modelAttribute="user">

    <form:input path="id" type="hidden" id="id"/>

    <div class="form-group">
        <form:label path="username" for="username">Имя пользователя:</form:label>
        <form:input path="username" type="text" class="form-control" id="username"/>
    </div>
    <div class="form-group">
        <form:label path="login" for="login">Логин:</form:label>
        <form:input path="login" type="text" class="form-control" id="login"/>
    </div>
    <div class="form-group">
        <form:label path="pass" for="password">Пароль:</form:label>
        <form:input path="pass" type="password" class="form-control" id="password"/>
    </div>
    <div class="form-group">
        <form:label path="roles" for="roles">Роль:</form:label>
        <form:select path="roles" multiple="true" class="form-control">
            <form:options items="${roles}" itemLabel="rolename" itemValue="id" />
        </form:select>
    </div>

    <button type="submit" class="btn btn-primary">Готово</button>
</form:form>

</body>
</html>