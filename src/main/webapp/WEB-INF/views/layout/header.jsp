<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"></sec:authentication>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Usan</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- HTML 혹은 JSP는 인터프린터로 읽기 때문에 script를 마지막에 넣는게 좋음-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Cos</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">

        <c:choose>
            <c:when test="${empty principal}">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
                    <li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" href="/umb/mappingForm">우산 대여하기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/umb/returnForm">우산 반납하기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/umb/fault/report">우산 고장신고</a></li>
                    <li class="nav-item"><a class="nav-link" href="/inquiry">문의 하기</a></li>

                    <c:if test="${principal.user.role.name() =='ADMIN' }">
                    <li class="nav-item"><a class="nav-link" href="/storage/joinForm">보관소 등록하기</a></li>
                     <li class="nav-item"><a class="nav-link" href="/umb/joinForm">우산 등록하기</a></li>
                     <li class="nav-item"><a class="nav-link" href="/storage/mappingForm">보관소에 우산 등록하기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/board">공지사항 작성</a></li>
                        <li class="nav-item"><a class="nav-link" href="/umb/fault/list">우산 고장목록</a></li>
                    </c:if>
                    <li class="nav-item"><a class="nav-link" href="#"> 안녕하세요 ${principal.user.nickName} 님 (${principal.user.role})</a></li>

                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
<br>

</body>
</html>