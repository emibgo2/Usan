let index={
    init: function () {
        $(".btn_submit#btn-umb-rent").on("click",()=>{
            this.rentTest();
        });
        $("#btn-umbrella-save").on("click",()=>{
            this.save();
        });
        $("#btn-storage-save").on("click",()=>{
            this.storage_save();
        });
    },

    save: function () {
        let data={
            umbrellaId:$("#umb_id").val(),
        }
        $.ajax({
            type: "POST",
            url: "/umb/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("우산 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("우산 등록이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    rent: function (locations,days) {
        let data={
            locations:locations,
            days:days
        };

        $.ajax({
            type: "POST",
            url:`/umb/rent/${locations}/${days}`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            async:false,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert(resp.response)
            if (resp.response === 500) {
                alert("우산 등록이 실패하였습니다.");
            }else if (resp.response === 2) {
                alert("이미 결제가 완료되었습니다.\n 보관소에서 비밀번호를 입력하세요");
            }else if (resp.response === 3) {
                alert("우산이 꽉 찼습니다 반납후 이용해주세요");
            } else  {
                alert("우산 등록이 완료되었습니다.");
                // console.log(resp)
                location.href="/umb/rent/success";
            }
        }).fail(function (error) {
            alert(" Error ->"+JSON.stringify(error));
        });
    },

    // rent: function (umbrellaId,rental_period) {
    //     let id = $("#id").val();
    //     let data={
    //         id:$("#userId").val(),
    //     };
    //     $.ajax({
    //         type: "PUT",
    //         url:`/umb/mapping/${umbrellaId}/${rental_period}`,
    //         data: JSON.stringify(data), // http body 데이터
    //         contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
    //         async:false,
    //         dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
    //         // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
    //     }).done(function (resp) {
    //         if (resp.status === 500) {
    //             alert("우산 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
    //         }else if (resp.response === 3) {
    //             alert("우산이 꽉 찼습니다 반납후 이용해주세요");
    //         } else if (resp.response === 1){
    //             alert("우산 등록이 완료되었습니다.");
    //             // console.log(resp)
    //             location.href = "/";
    //         }
    //     }).fail(function (error) {
    //         alert(id+"<- ID / Error ->"+JSON.stringify(error));
    //     });
    // },
    return: function (umbrellaId) {
        let id = $("#id").val();

        let data={
            id:$("#userId").val(),

        };
        $.ajax({
            type: "PUT",
            url:`/umb/return/${umbrellaId}`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            async:false,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("우산 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
            }else if (resp.response === 3) {
                alert("오류가 발생했습니다.");
            } else  {
                alert("우산 반납이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {

            alert(id+"<- ID / Error ->"+JSON.stringify(error));
        });
    },
    storage_save: function () {
        let data={
            umbrellaId: $("#storage_id").val(),
        }
        $.ajax({
            type: "POST",
            url: "/storage/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("보관소 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("보관소 등록이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    storage_mapping: function (umbrellaId) {
        let id =$("#id").val();
        let data={
            umbrellaId: umbrellaId
        }
        alert(data.umbrellaId);
        $.ajax({
            type: "POST",
            url: `/storage/${id}/umb/${umbrellaId}/mapping`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            async:false,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("우산 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("우산 등록이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    fault_ReportUmbrella: function (umbrellaId) {
        let id =$("#id").val();
        let data={
            umbrellaId: umbrellaId
        }
        alert(data.umbrellaId);
        $.ajax({
            type: "PUT",
            url: `/umb/${umbrellaId}/fault`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            async:false,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("우산 고장 등록이 실패하였습니다 \n아이디가 중복되었습니다.");
            } else {
                alert("우산 고장 등록이 완료되었습니다.");
                // console.log(resp)
                location.href = "/";
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init()