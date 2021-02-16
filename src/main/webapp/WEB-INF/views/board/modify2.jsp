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
<script>
$(function(){
	$('#remove-btn').click(function(e){
		e.preventDefault();
		$('#modify-form').attr('action', '${root}/board/remove');
		$('#modify-form').submit();
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<u:navbar />

<div class="container-sm">
	<div class="row">
		<div class="col-12 col-sm-6 offset-sm-3">
			<h1>게시물 수정</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-sm-6 offset-sm-3">
			<form method="post" enctype="multipart/form-data" id="modify-form" action="${root }/board/modify2">
			<%-- 같은 경로 action 생략 가능 get/post 차이 => 이렇게 하면 현재 주소창으로 action됨 -> input 추가 시 값이 중복으로 붙음 (like checkbox) --%>
				<div class="form-group">
					<label for="bno">번호</label>
					<input name="bno" type="text" value="<c:out value='${board.bno }' />"
						class="form-control" id="bno" readonly>
				</div>
			
				<div class="form-group">
					<label for="input1">제목</label>
					<input name="title" type="text" value="<c:out value='${board.title }' />"
						class="form-control" id="title">
				</div>

				<div class="form-group">
					<label for="textarea">내용</label>
					<textarea name="content" class="form-control" id="textarea" rows="3"><c:out value="${board.content }" /></textarea>
				</div>
				
				<div class="form-group">
					<label for="file">파일</label>
					<input type="file" accept="image/*" name="file" class="form-control" id="file"> <!-- multiple -->
				</div>

				<div class="form-group">
					<label for="writer">작성자</label>
					<input name="writer" type="text" value="<c:out value='${board.writer }' />"
						class="form-control" id="writer" readonly>
				</div>
					<%-- action 생략,, =>>> 파라미터로 받은 criteria가 알아서 같이 submit됨 --%>
				<input hidden=true value="${criteria.type }" name="type" />
				<input hidden=true value="${criteria.keyword }" name="keyword" />
				<input hidden=true value="${criteria.pageNum }" name="pageNum" />
				<input type="hidden" value="${criteria.amount }" name="amount" />
				<button type="submit" class="btn btn-primary">등록</button>
				<c:url value="/board/get" var="cancelLink">
		      		<c:param name="bno" value="${board.bno }"/>
		      		<c:param name="type" value="${criteria.type }"/>
			    	<c:param name="keyword" value="${criteria.keyword }"/>
					<c:param name="pageNum" value="${criteria.pageNum }" />      	
					<c:param name="amount" value="${criteria.amount }" />      	
	      		</c:url>
				<a href="${cancelLink }" type="submit" class="btn btn-secondary">취소</a>
				<button id="remove-btn" type="submit" class="btn btn-danger float-right">삭제</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>