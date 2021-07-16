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
    <h1>storage_detail 페이지 입니다</h1>
    <h1>보관소 번호:${storage.id}</h1>
    <h1>현재 유저 ID: ${principal.user.id}</h1>

    <h1>현재 유저 ID: ${principal.user.nickName}</h1>

        <br>
        ${storage}
        <h2>${storage.id}번 보관소가 갖고 있는 우산:
        <c:forEach var="umbrella" items="${storage.umbrellaList}">
            <c:if test="${umbrella.user_id == 0}">
         <button class="btn btn-success"  onClick="index.rent(${umbrella.id})"> ${umbrella.id}번 우산</button>
            </c:if>
        </c:forEach>
        </h2>

        <c:forEach var="umbrellas" items="${umbrella}">
            <c:choose>
            <c:when test="${(umbrellas.user_id == 0 && umbrellas.storage.id != storage.id) && umbrellas.storage.id==null}">
            <button class="btn btn-primary"  onClick="index.rent(${umbrellas.id})"> ${umbrellas}번 우산</button>
            </c:when>
            </c:choose>
        </c:forEach>

    </form>
</div>

<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
