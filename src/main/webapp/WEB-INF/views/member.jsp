<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <link rel="stylesheet" href="/css/login.css"/>
  <style>
    @import url(css/login.css);
  </style>
</head>

<body>
<div id="content">
  <img class="logo" alt="logo" src="images/logo.png">
  <form action="/login" method="post">
    <input type="text" name="userId" class="login_input_text" placeholder="아이디" required><br>
    <input type="text" name="userPw" class="login_input_text" placeholder="비밀번호" required><br>
    <input type="email" name="userEmail" class="login_input_text" placeholder="이메일" required><br>
    <input type="text" name="userName" class="login_input_text" placeholder="이름" required><br>
    <input type="text" name="userBirtyday" class="login_input_text" placeholder="생년월일 8자리" required><br>
    <input type="password" name="userPhone" class="login_input_text" placeholder="휴대전화번호" required><br>
    <input type="submit" class="login_input_btn" value="회원가입">
  </form>
  </div>
</body>

</html>