<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script>
$(function(){
	$('#outside').click(function(){
		$('form').submit();
	});
	
	$('#inside').click(function(e){
		e.preventDefault();/* 원래 하는 일 X */
		console.log('inside btn');
		$('form').submit();
	});
});
</script>
</head>
<body>
<h1>SUBMIT 예제</h1>
<h1>name: ${param.name }</h1>
<form action=""> <%-- 같은 곳으로 submit --%>
	<input type="text" name="name" value="java" /> <br>
	<input type="submit" value="submit" />
	<button id="inside">또 다른 전송</button>
</form>

<button id="outside">밖에 있는 버튼</button>
</body>
</html>