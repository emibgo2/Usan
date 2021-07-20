<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Kingname Spring boot</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $.ajax({
            type: "GET",
            url: "/string",
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json", // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            success: (data) => {
                console.log(data[0]);
                $('#string').html(data[0].umbrellaList);
                var a= data[0].umbrellaList;
                $(document).ready(function(){
                    var num = 12;
                });
                var bb =document.getElementById('string');
                bb.innerText = a;
            }

        });

    </script>
</head>
<body>
<h1>Hello world!</h1>
<span>우산 번호</span>
<h1>d</h1>
dd
<H1 id="string" ></H1>

</body>
</html>

