<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kingname Spring boot</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $.ajax({
            type: "GET",
            url: "/string",
            success: (data) => {
                console.log(data);
                $('.string').html(data);
            }
        });
    </script>
</head>
<body>
<h1>Hello world!</h1>
<div class="string"></div>
</body>
</html>

