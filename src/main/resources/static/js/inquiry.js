let index= {
    init: function () {
        $("#btn-save").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.deleteById();
        });
        $("#btn-inquiry-update").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.update();
        });
        $("#btn-answer-save").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.answer();
        });
    },
    save: function () {
        // alert('user의 save함수 호출됨');
        let data={
            title:$("#title").val(),
            content:$("#content").val(),
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "POST",
            url: "/api/inquiry",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("문의 작성 완료.");
            // console.log(resp)
            location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/inquiry/"+id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            alert("문의 글 삭제가 완료되었습니다.");
            // console.log(resp)
            location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();
        let data={
            title:$("#title").val(),
            content:$("#content").val(),
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "patch",
            url: "/api/inquiry/"+id,
            data: JSON.stringify(data), // http body 데이터
            async:false,
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("문의 글 수정이 완료되었습니다.");
            // console.log(resp)
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    answer: function () {
        let id = $("#inquiryId").val();
        let userId = $("#userId").val();
        let data={
            title:$("#title").val(),
            content:$("#content").val(),

        };

        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "put",
            url: `/api/inquiry/${id}/answer/${userId}`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("문의 작성 완료.");
            // console.log(resp)
            location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

}

index.init()