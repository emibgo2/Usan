
<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>
=
<div class="container">
    <div class="form-group">
        <a href="/inquiry/saveForm"> <button class="btn btn-primary">작성</button></a>
        <a href="/inquiry/saveForm"> <button class="btn btn-primary">작성</button></a>
    </div>

    <c:forEach var = "inquiry" items = "${inquirys.content}">
        <c:if test="${inquiry.answer==true}}">
        <div class="card" >
            <div class="card-body">
                <h4 class="card-title">${inquiry.title}</h4>
                <a href="/inquiry/${inquiry.id}" class="btn btn-primary">상세 보기</a>
            </div>
        </div>
        </c:if>
    </c:forEach>

    <br>
    <ul class="pagination justify-content-center">

        <c:choose>
            <c:when test = "${inquirys.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${inquirys.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${inquirys.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test = "${inquirys.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${inquirys.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${inquirys.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<script src="/js/inquiry.js"></script>
<%@include file="../layout/footer.jsp"%>
