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
	
	$('#cancel-btn').click(function(e){
		e.preventDefault();
		var bno = '${board.bno}';
		$('#cancel-btn').attr('action', '${root}/board/get?bno=${bno}');
		$('#cancel-btn').submit();
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
			<form method="post" id="modify-form"> <%-- 같은 경로 생략 가능 get/post 차이 --%> <%-- action="${root}/board/modify2" --%>
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
					<label for="writer">작성자</label>
					<input name="writer" type="text" value="<c:out value='${board.writer }' />"
						class="form-control" id="writer" readonly>
				</div>

				<input hidden=true value="${criteria.pageNum }" name="pageNum" />
				<input type="hidden" value="${criteria.amount }" name="amount" />
				<button type="submit" class="btn btn-primary">등록</button>
				<button id="cancel-btn" type="submit" class="btn btn-secondary">취소</button>
				<button id="remove-btn" type="submit" class="btn btn-danger float-right">삭제</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>