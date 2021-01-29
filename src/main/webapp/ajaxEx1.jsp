<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script>
$(function(){
	/* 
		$.ajax()로 client -> server 요청
	  	server가 ResponseEntity로 응답
	*/
	$('#btn-1').click(function(){
		$.ajax({
			method: 'POST',
			url: '/replies/new',
			data: '{"bno": 205, "reply": "new reply", "replyer": "user01"}',// json ""
			contentType: 'application/json; charset=UTF-8'// request의 contentType
		});
	});
	
	$('#btn-2').click(function(){
		var bno = Number(205);
		var page = Number(1);
		/*$.ajax({
			url: '/replies/pages/' + bno + '/' + page
		});*/
		$.ajax('/replies/pages/' + bno + '/' + page);
	});
	
	$('#btn-3').click(function(){
		var rno = Number(45);
		$.ajax({
			url: '/replies/' + rno
		});
	});
	
	$('#btn-4').click(function(){
		var rno = Number(44);
		$.ajax({
			method: 'DELETE',
			url: '/replies/' + rno
		});
	});
	
	$('#btn-5').click(function(){
		var rno = Number(47);
		$.ajax({
			type: 'PUT',/* method == type(옛날 버전) */
			url: '/replies/' + rno,
			data: '{"reply": "modified reply3"}',
			contentType: 'application/json; charset=UTF-8'
		});
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1>AJAX ex1</h1>
	<button id="btn-1" class="btn btn-primary">New Reply</button> <br>
	<button id="btn-2" class="btn btn-info">Get Reply List</button> <br>
	<button id="btn-3" class="btn btn-light">Get Reply</button> <br>
	<button id="btn-4" class="btn btn-danger">Delete Reply</button> <br>
	<button id="btn-5" class="btn btn-warning">Modify Reply</button>
</div>
</body>
</html>