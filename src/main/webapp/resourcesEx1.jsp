<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>
<h1>resources ex1</h1>
<div>
	<img alt="AJAX" src="ajax.jpg"> <%-- controller에서 /ajax 경로를 찾아서 not found --%>
	<img alt="AJAX resources folder" src="resources/ajax.jpg"> <%-- controller에서 /ajax 경로를 찾아서 not found --%>
	<%-- resources folder에 자원 담아놓기, servlet-context.xml에 명시되어 있음 --%>
</div>
</body>
</html>