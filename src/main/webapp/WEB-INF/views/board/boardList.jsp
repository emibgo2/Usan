<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <div class="form-group">
        <c:if test="${principal.user.role.name() =='ADMIN' }">

            <a href="/board/saveForm"> <button class="btn btn-primary">작성</button></a>
        </c:if>
    </div>

    <c:forEach var = "board" items = "${boards.content}">
        <div class="card" >
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary">상세 보기</a>
            </div>
        </div>
    </c:forEach>
    <br>
    <ul class="pagination justify-content-center">

        <c:choose>
            <c:when test = "${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test = "${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>