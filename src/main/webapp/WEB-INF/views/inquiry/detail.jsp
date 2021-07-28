<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${inquiry.user.id==principal.user.id}">
        <a href="/inquiry/${inquiry.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br><br>
    <div>
        글 번호:<span id="id"><i>${inquiry.id} </i></span>
           작성자 :<span><i>${inquiry.user.nickName} </i></span>

    </div>
    <h3>${inquiry.title}</h3>
    <hr>
        <div>
            ${inquiry.content}
        </div>
    <hr>
        <div>
<%--            type="button" id="btn-like-save"--%>
        </div>
    <hr>
    <c:if test="${principal.user.role.name() =='ADMIN' }">
        <div class="container">
            <form >
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" placeholder="Enter title" id="title">
                </div>

                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea class="form-control summernote" rows="5" id="content"></textarea>
                </div>


            </form>
            <button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
        </div>
    </c:if>
    <c:if test="${inquiry.answer==null}">
        <h2>아직 답변이 오지 않았습니다. 최대한 빠른 시일 내에 답변 드리겠습니다.</h2>
    </c:if>
</div>

<script src="/js/inquiry.js"></script>


<%@include file="../layout/footer.jsp"%>
