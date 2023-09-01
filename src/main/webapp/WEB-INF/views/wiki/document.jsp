<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="document" scope="request" type="com.gamejigi.wiki.domain.document.Document"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="/util/util.js"></script>
    <meta charset="UTF-8">
    <title>위키 - 문서</title>
</head>
<body>

문서 페이지 입니다

<br/>
<hr/>
<br/>

문서 :

<br/>

id : ${document.id}

<br/>

수정시간 : ${document.modifiedDate}

<br/>

수정횟수 : ${document.modifyingCount}

<br/>

문서이름 : ${document.name}

<br/>

<label>
<textarea>
    ${document.text}
</textarea>
</label>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>