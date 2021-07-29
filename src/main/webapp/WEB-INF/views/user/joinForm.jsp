<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <div class="form-group">
            <label for="username">Username</label>

            <input type="text" class="form-control" placeholder="Enter username" id="username">
            <button id="btn-id-check" class="btn btn-danger">중복 확인</button>
        </div>
        <div class="form-group">
            <label for="nickName">Nick Name</label>
            <input type="text" class="form-control" placeholder="Enter Nick name" id="nickName">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number</label>
             <input type="phoneNumber" class="form-control" placeholder="Enter Your Phone number" id="phoneNumber">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
    <button id="btn-ADMIN-save" class="btn btn-primary">관리자 회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>
