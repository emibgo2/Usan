<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <div class="form-group">

            <c:forEach var="storages" items="${storage}">
                <button class="btn btn-success" type="button" onclick="location.href='http://localhost:8080/storage/${storages.id}'"> ${storages.id}번 보관소</button>
            </c:forEach>
            <br>
            <label for="umb_id">우산 일련번호</label>
            <input type="text" class="form-control" placeholder="우산의 일련번호를 입력하세요" id="umb_id">
        </div>
    </form>
    <button id="btn-umbrella-save" class="btn btn-primary">우산 등록</button>
</div>

<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
