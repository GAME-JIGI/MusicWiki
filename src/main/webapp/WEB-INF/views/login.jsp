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
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        // 카카오 SDK 초기화
        Kakao.init('bbb959fac6c8fb113822ad5770dcdbaf');

        // 카카오 소셜 로그인 함수
        function loginWithKakao() {
            Kakao.Auth.login({
                success: function(authObj) {
                    alert('로그인 성공\n사용자 아이디: ' + authObj.id);
                },
                fail: function(err) {
                    alert('로그인 실패: ' + JSON.stringify(err));
                }
            });
        }
    </script>
    <script>
        // 네이버 소셜 로그인 함수
        function loginWithNaver() {
            // 네이버 로그인 URL을 생성합니다.
            var redirectUri = encodeURIComponent('http://localhost:9000');
            var clientId = 'pHC9jmMbF5xYg_EGa0tG';
            var naverLoginUrl = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + clientId + '&redirect_uri=' + redirectUri + '&state=STATE_STRING';

            // 사용자를 네이버 로그인 페이지로 리다이렉트합니다.
            window.location.href = naverLoginUrl;
        }
    </script>
    <script>
        // 구글 소셜 로그인 함수
        function loginWithGoogle() {
            // 구글 로그인 URL을 생성합니다.
            var clientId = '11750994194-9qnff01gc21dt6jmkquuno50smpjckdn.apps.googleusercontent.com';
            var redirectUri = 'http://localhost:9000';
            var scope = 'profile email'; // 필요한 스코프 설정
            var googleLoginUrl = 'https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=' + clientId + '&redirect_uri=' + encodeURIComponent(redirectUri) + '&scope=' + encodeURIComponent(scope);

            // 사용자를 구글 로그인 페이지로 리다이렉트합니다.
            window.location.href = googleLoginUrl;
        }
    </script>
</head>

<body>
<div id="content">
    <img class="logo" alt="logo" src="images/logo.png">
    <form action="/login" method="post">
        <input type="text" name="userId" class="login_input_text" placeholder="아이디" required><br>
        <input type="password" name="passWd" class="login_input_text" placeholder="비밀번호" required><br>
        <input type="submit" class="login_input_btn" value="Login">
        <input type="button" class="login_input_google_btn" value="구글 로그인" onClick="location.href='javascript:loginWithGoogle()'">
        <input type="button" class="login_input_naver_btn" value="네이버 로그인" onClick="location.href='javascript:loginWithNaver()'">
        <input type="button" class="login_input_kakao_btn" value="카카오 로그인" onClick="location.href='javascript:loginWithKakao()'">
    </form>
    <div class="find_info"><br>
        <a id="find_id" href="#">아이디 찾기</a><a> |</a>
        <a id="find_password" href="#">비밀번호 찾기</a><a> |</a>
        <a id="find_password" href="member">회원가입</a><a><br><br>
        <a class="sub_font_style" href="#"><b style="color:#7b7b7b">개인정보처리방침</b></a><br>
        <a class="question" href="#">회원 정보 문의: gamejigi.induk.ac.kr</a>
    </div>
    <p id="copyright">Copyright © MusicWiki All Rights Reserved.</p>
</div>
</body>

</html>
