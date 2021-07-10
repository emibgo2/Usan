<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form >
    <input type="hidden" id="userId" value="${sessionScope.principal.id}">
    <input type="hidden" id="userPassword" value="${sessionScope.principal.password}">
    <input type="hidden" id="userName" value="${sessionScope.principal.username}">
    <input type="hidden" id="userEmail" value="${sessionScope.principal.email}">
    <input type="hidden" id="userPhoneNumber" value="${sessionScope.principal.phoneNumber}">
    <input type="hidden" id="userNickname" value="${sessionScope.principal.nickName}">
    <h1>현재 유저 ID: ${sessionScope.principal.id}</h1>

    <h1>현재 유저 ID: ${sessionScope.principal.nickName}</h1>

        <c:forEach var="umbrellas" items="${umbrella}">
        <button class="btn btn-primary" onclick="rent(${umbrellas.id}), index.rent()"> ${umbrellas.id}번 우산</button>
        </c:forEach>

    <button id="btn-umbrella-rent" class="btn btn-primary">우산 대여:</button>
    </form>
</div>

<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
