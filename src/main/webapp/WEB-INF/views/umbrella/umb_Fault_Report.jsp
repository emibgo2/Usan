<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="userId" value="${principal.user.id}">
        <input type="hidden" id="userPassword" value="${principal.user.password}">
        <input type="hidden" id="userName" value="${principal.user.username}">
        <input type="hidden" id="userEmail" value="${principal.user.email}">
        <input type="hidden" id="userPhoneNumber" value="${principal.user.phoneNumber}">
        <input type="hidden" id="userNickname" value="${principal.user.nickName}">
        <h1>현재 유저 ID: ${principal.user.id}</h1>

        <h1>현재 유저 ID: ${principal.user.nickName}</h1>
        <h1>umbrella_Id1:${principal.user.umbrella_Id1 }</h1>
        <h1>umbrella_Id2:${principal.user.umbrella_Id2 }</h1>
        ${umbrella}
        <c:forEach var="umbrellas" items="${umbrella}">
        <c:if test="${umbrellas.user_id == principal.user.id  && umbrellas.rent_date !=null}">
        <div>
            <c:if test="${umbrellas.failure_status==null}">
            <button class="btn btn-primary" onClick="ins.test(${umbrellas.id})"> ${umbrellas.id}번 우산 고장 신고</button>
            </c:if>
            <h5>빌린날: ${umbrellas.rent_date}</h5>
            <h5>반납 날짜: ${umbrellas.rent_end_date}</h5>
            <c:if test="${umbrellas.failure_status!=null}">
                <button class="btn btn-secondary">${umbrellas.id}번 우산 고장 접수 완료</button>

            </c:if>
            <c:if test="${umbrellas.over_date <=0 && umbrellas.failure_status==null}">
            <h5>연체: 반납일 까지 ${umbrellas.over_date*umbrellas.over_date}일 남았습니다</h5>
            </c:if>
            <c:if test="${umbrellas.over_date >=0 && umbrellas.failure_status==null}">
                <h5>연체: 반납일로 부터 ${umbrellas.over_date}일 지났습니다</h5>
            </c:if>
        </div>
            </c:if>
            </c:forEach>


    </form>
</div>
<script>
    let ins={
        test:function(umbrellaId){
            if (confirm(umbrellaId+"번 우산을 고장신고하시겠습니까?")) {
                index.fault_ReportUmbrella(umbrellaId)
            } else {

            }
        }
    }
</script>
<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
