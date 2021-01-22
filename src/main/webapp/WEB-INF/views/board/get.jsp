<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
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
<u:navbar />
	
<div class="container-sm">
	<div class="row">
		<div class="col-12 col-sm-6 offset-sm-3">
			<h1>게시물 보기</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-sm-6 offset-sm-3">
			<div class="form-group">
				<label for="bno">번호</label>
				<input type="text" value="<c:out value='${board.bno }' />"
					class="form-control" id="bno" readonly>
			</div>
			
			<div class="form-group">
				<label for="input1">제목</label>
				<input type="text" value="<c:out value='${board.title }' />"
					class="form-control" id="title" readonly>
			</div>

			<div class="form-group">
				<label for="textarea">내용</label>
				<textarea name="content" class="form-control" id="textarea" rows="3" readonly><c:out value="${board.content }" /></textarea>
			</div>

			<div class="form-group">
				<label for="input1">작성자</label>
				<input name="writer" type="text" value="<c:out value='${board.writer }' />"
					class="form-control" id="writer" readonly>
			</div>
			
			<c:url value="/board/modify" var="modifyLink">
				<c:param value="${board.bno }" name="bno" />
				<c:param value="${criteria.pageNum }" name="pageNum" />      	
				<c:param value="${criteria.amount }" name="amount" />      	
	      	</c:url>
			<a href="${modifyLink }" class="btn btn-default">수정</a>
			
			<c:url value="/board/remove" var="removeLink">
				<c:param value="${board.bno }" name="bno" />
				<c:param value="${criteria.pageNum }" name="pageNum" />      	
				<c:param value="${criteria.amount }" name="amount" />      	
	      	</c:url>
			<a href="${removeLink }" class="btn btn-secondary">삭제</a>
			
			<c:url value="/board/list" var="listLink">
				<c:param value="${criteria.pageNum }" name="pageNum" />      	
				<c:param value="${criteria.amount }" name="amount" />      	
	      	</c:url>
			<a href="${listLink }" class="btn btn-primary float-right">목록</a>
		</div>
	</div>
</div>
</body>
</html>