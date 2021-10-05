let index={
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-ADMIN-save").on("click", ()=>{
            this.adminSave();
        })
        $("#btn-id-check").on("click", ()=>{
            this.check();
        })
    },
    save: function () {
        let data={
            username:$("#username").val(),
            nickName: $("#nickName").val(),
            password: $("#password").val(),
            phoneNumber: $("#phoneNumber").val(),
            email: $("#email").val(),
        }
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.data == 3) {
                alert("회원가입이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    adminSave: function () {
        let data={
            username:$("#username").val(),
            nickName: $("#nickName").val(),
            password: $("#password").val(),
            phoneNumber: $("#phoneNumber").val(),
            email: $("#email").val(),
        }
        $.ajax({
            type: "POST",
            url: "/auth/admin/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.data === 3) {
                alert("회원가입이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    check: function () {
        let data={
            username:$("#username").val(),
            button:$("#btn-id-check").val(),
        }
        $.ajax({
            type: "POST",
            url: "/auth/id/check",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            async:false,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.data == 2) {
                alert("이미 존재하는 아이디 입니다.")
            } else {
                alert("사용가능한 아이디입니다.");
                // console.log(resp)
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init()