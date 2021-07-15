<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form >
    <input type="hidden" id="userId" value="${principal.user.id}">
    <input type="hidden" id="userPassword" value="${principal.user.password}">
    <input type="hidden" id="userName" value="${principal.user.username}">
    <input type="hidden" id="userEmail" value="${principal.user.email}">
    <input type="hidden" id="userPhoneNumber" value="${principal.user.phoneNumber}">
    <input type="hidden" id="userNickname" value="${principal.user.nickName}">
    <h1>현재 유저 ID: ${principal.user.id}</h1>

    <h1>현재 유저 ID: ${principal.user.nickName}</h1>
        <c:forEach var="storages" items="${storage}">
            <button class="btn btn-success" type="button" onclick="location.href='/storage/admin/${storages.id}'"> ${storages.id}번 보관소</button>
        </c:forEach>
        <br>

    </form>
</div>

<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
