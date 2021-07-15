<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <div class="form-group">
            <label for="storage_id">보관소 일련번호</label>
            <input type="text" class="form-control" placeholder="보관소 일련번호를 입력하세요" id="storage_id">
        </div>
    </form>
    <button id="btn-storage-save" class="btn btn-primary">보관소 등록</button>
</div>

<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
