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
        @import url(/css/login.css);
    </style>
</head>

<body>
<div id="content">
    <img class="logo" alt="logo" src="images/logo.png">
    <form>
        <input type="text" name="id" class="login_input_text" placeholder="ID"><br>
        <input type="password" name="password" class="login_input_text" placeholder="Password"><br>
        <input type="submit" class="login_input_btn" value="Login">
    </form>
    <div class="find_info"><br>
        <a id="find_id" href="#">아이디 찾기</a><a> |</a>
        <a id="find_password" href="#">비밀번호 찾기</a><a> |</a>
        <a id="find_password" href="#">비밀번호 찾기</a><a><br><br>
        <a class="sub_font_style" href="#"><b style="color:#7b7b7b">개인정보처리방침</b></a><br>
        <a class="question" href="#">회원 정보 문의: admin@site.net</a>
    </div>
    <p id="copyright">Copyright © MusicWiki All Rights Reserved.</p>
</div>
</body>

</html>
