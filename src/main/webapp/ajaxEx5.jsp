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
	$('#btn-1').click(function(){
		$.ajax('/replies/new', {
			type: 'POST',
			contentType: 'application/json',
			data: '{"bno": 205, "reply": "new Reply@!", "replyer": "user02"}',
			success: function(data, status, xhr){
				console.log('등록 성공');
				// console.log(jqXHR.responseText);				
				console.log(data);// = xhr.responseText
			},
			error: function(){
				console.log('등록 실패');
			}
		});
	});
	
	$('#btn-2').click(function(){
		$.ajax('/replies/new', {
			type: 'POST',
			contentType: 'application/json',
			data: '{"bno": 205, "replyer": "user02"}',// not null
			complete: function(jqXHR, status){// 요청 끝난 후 실행
				console.log(status);// error
			}
		});
	});
	
	$('#btn-3').click(function(){
		$.ajax('/replies/new', {
			type: 'POST',
			contentType: 'application/json',
			data: '{"bno": 205, "reply": "new Reply@!!", "replyer": "user02"}'
		}).done(function(data, status, xhr){/* ajax가 리턴하는 xhr.done() */
			console.log(data);
		}).fail(function(){
			console.log('등록 실패');
		});
	});
	
	$('#btn-4').click(function(){
		$.ajax('/replies/pages/205/1', {
		}).done(function(data, status, xhr){
			console.log(status);// success
			console.log(data);// List<ReplyVO>	
		}).fail(function(){
			console.log('실패');
		});
	});
	
	$('#btn-5').click(function(){
		$.ajax('/replies/50', {
		}).done(function(data, status, xhr){
			console.log(status);
			console.log(data);// ResponseEntity에 담긴 ReplyVO 객체
		}).fail(function(){
			console.log('실패');
		});
	});
	
	$('#btn-6').click(function(){
		$.ajax('/replies/52', {
			method: 'DELETE',
		}).done(function(data, status, xhr){
			console.log(data);
		}).fail(function(){
			console.log('삭제 실패');
		});
	});
	
	$('#btn-7').click(function(){
		$.ajax('/replies/50', {
			method: 'PUT',
			contentType: 'application/json',
			data: '{"reply": "i have done modifying~!!!"}'
		}).done(function(data, status, xhr){
			console.log(data);
		}).fail(function(){
			console.log('수정 실패');
		});
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1>AJAX ex5</h1>
	<button id="btn-1" class="btn btn-primary">New Reply: Success</button>
	<button id="btn-2" class="btn btn-primary">New Reply: Error</button> <br>
	<button id="btn-3" class="btn btn-danger">New Reply: ?</button> <br>
	<button id="btn-4" class="btn btn-dark">Get Reply List</button> <br>
	<button id="btn-5" class="btn btn-white">Get Reply</button> <br>
	<button id="btn-6" class="btn btn-secondary">Delete Reply</button> <br>
	<button id="btn-7" class="btn btn-info">Modify Reply</button> <br>
</div>
</body>
</html>