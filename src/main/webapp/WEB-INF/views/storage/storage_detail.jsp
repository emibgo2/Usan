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
        <div class="form-group">
            <label for="rental_period">우산 대여기간</label>
            <input type="number" class="form-control" placeholder="우산을 대여할 기간을 적어주세요 ( 일 단위 )" id="rental_period" value="1" min="1" max="20">
        </div>
        <h2>${storage.id}번 보관소가 갖고 있는 우산:
        <c:forEach var="umbrella" items="${storage.umbrellaList}">
            <c:if test="${umbrella.user_id == 0 && umbrella.failure_status==null}">
         <button class="btn btn-success"  onClick="inse.test(${umbrella.id})"> ${umbrella.id}번 우산</button>
            </c:if>
        </c:forEach>
        </h2>




    </form>
</div>
<script>
    let inse={
        test:function(umbrellasId){
            const rentalPeriod = document.getElementById('rental_period').value
            if (confirm(umbrellasId+"우산을 "+rentalPeriod+"일 동안 대여하시겠습니까?")) {
                index.rent(umbrellasId,rentalPeriod)
            } else {

            }
        }
    }
</script>
<script src="/js/umbrella.js"></script>
<%@include file="../layout/footer.jsp"%>
