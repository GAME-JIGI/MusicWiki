<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form action="/login" method="post">
    <label>
        ID :
    </label>
    <input type="text" name="userId" />
    <label>
        PW :
    </label>
    <input type="password" name="passWd" />
    <button type="submit">로그인</button>
</form>
<hr>
<form action="/logout" method="post">
    <button type="submit">로그아웃</button>
</form>
</body>
</html>