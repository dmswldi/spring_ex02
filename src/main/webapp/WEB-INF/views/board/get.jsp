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
var appRoot = '${root}';/* js는 톰캣이 완성하는 코드가 아니라 소스 코드 그 자체임 */
var bno = ${board.bno};/* jsp 코드임 */
</script>
<script src="${root }/resources/js/reply.js"></script>
<script>
$(function(){
	
	function showList(){
		replyService.getList({bno: bno}, function(list){
			
			for(var i = 0; i < list.length; i++){
				//console.log(list[i].replyDate.getMonth);
				//var replyDate2 = new Date(list[i].replyDate); 
				//var replyDate = list[i].replyDate.getFullYear + '/' + list[i].replyDate.getMonth + 1 + '/' + list[i].replyDate.getDate;
				var replyLI = '<li class="media" data-rno="' + list[i].rno + '">'
					+ '<div class="media-body">'
					+ '<h5>' + list[i].replyer + ' <small>' + list[i].replyDate + '</small></h5>'
					+ list[i].reply
					+ '<hr></div>'
					+ '</li>';
				$('ul').append(replyLI);
			}
			
		}, function(err){
			console.log(err);
		});
	};
	
	
	$('#new-reply-button').click(function(){
		$('#new-reply-modal').modal('show');/* modal 사용법 */
	});

	$('#reply-submit-button').click(function(){
		var reply = $('#reply-input').val();
		var replyer = $('#replyer-input').val();
		var data = {bno:bno, reply:reply, replyer:replyer};
		
		replyService.add(data, function(){
			alert('댓글이 등록되었습니다.');
			$('li').remove();// li 포함 태그 및 요소 지우기
			showList();// 댓글 목록 가져오기
		}, function(err){
			alert('댓글 등록에 실패하였습니다.');
			console.log(err);
		});
		$('#new-reply-modal').modal('hide');
		$('.modal input').val('');
	});
	
	// 댓글 목록 가져오기 실행
	showList();
	
});
</script>
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
			
			<c:url value="/board/modify2" var="modifyLink">
				<c:param name="bno" value="${board.bno }" />
				<c:param name="type" value="${criteria.type }"/>
		    	<c:param name="keyword" value="${criteria.keyword }"/>
				<c:param name="pageNum" value="${criteria.pageNum }" />      	
				<c:param name="amount" value="${criteria.amount }" />      	
	      	</c:url>
			<a href="${modifyLink }" class="btn btn-default">수정</a>
			
			<c:url value="/board/remove" var="removeLink">
				<c:param name="bno" value="${board.bno }" />
				<c:param name="type" value="${criteria.type }"/>
		    	<c:param name="keyword" value="${criteria.keyword }"/>
				<c:param name="pageNum" value="${criteria.pageNum }" />      	
				<c:param name="amount" value="${criteria.amount }" />      	
	      	</c:url>
			<a href="${removeLink }" class="btn btn-secondary">삭제</a>
			
			<c:url value="/board/list" var="listLink">
				<c:param name="type" value="${criteria.type }"/>
		    	<c:param name="keyword" value="${criteria.keyword }"/>
				<c:param name="pageNum" value="${criteria.pageNum }" />      	
				<c:param name="amount" value="${criteria.amount }" />      	
	      	</c:url>
			<a href="${listLink }" class="btn btn-primary float-right">목록</a>
		</div>
	</div>
</div>

<%-- Reply --%>
<div class="container-sm mt-3">
	<div class="row">
		<div class="col-12 col-sm-6 offset-sm-3">
			<div class="card">
				<div class="card-header d-flex justify-content-between align-items-center">
					<span>댓글 목록</span>
					<button class="btn btn-info" id="new-reply-button">댓글 등록</button>
				</div>
				
				<div class="card-body">
					<ul class="list-unstyled">
						
					<%--
						<li class="media" data-rno="21">
							<div class="media-body">
								<h5>${reply.replyer} <small>${reply.replyDate}</small></h5>
								${reply.reply}
							<hr>
							</div>
						</li>
					 --%>						
						
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<%-- new Reply Modal --%>
<div class="modal fade" id="new-reply-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">새 댓글</h5>
				<button class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="reply-input" class="col-form-label">댓글</label>
					<input type="text" class="form-control" id="reply-input">
				</div>
				<div class="form-group">
					<label for="replyer-input" class="col-form-label">작성자</label>
					<input type="text" class="form-control" id="replyer-input">
				</div>
			</div>
			
			<div class="modal-footer">
				<button class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button id="reply-submit-button" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>