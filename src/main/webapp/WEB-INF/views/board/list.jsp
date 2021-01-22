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
	var result = '${result }';/* '' 처리 */
	
	checkModal(result);

	history.replaceState({}, null, null);// 등록 후 뒤로 가기할 때 모달 show x (모달창 중복 띄우기 XX)
	
	function checkModal(result){
		if(!history.state){// state가 null일 때
			if(result == 'modSuccess'){// registerd가 success일 때는 수정 
				$('#registerModal .modal-body p').html("게시글이 수정되었습니다.");
				$('#registerModal').modal("show");// bootstrap method
			} else if(result == 'delSuccess'){
				$('#registerModal .modal-body p').html("게시글이 삭제되었습니다.");
				$('#registerModal').modal("show");
			} else if(result){/* null, ""도 false 처리됨 */
				$('#registerModal .modal-body p').html("게시글 " + registerd + "번이 등록되었습니다.");
				$('#registerModal').modal("show");// bootstrap method
			}
		}
	}
	/*
	var actionForm = $('#actionForm');
	$('.pagination a').click(function(e){
		e.preventDefault();
		
		actionForm.find("[name='pageNum']").val($(this).attr('href'));
		actionForm.submit();
	});*/
});
</script>

<style>
th, td {
	white-space: nowrap;
}
.title {
	color: black;
	overflow: hidden;
 	text-overflow: ellipsis;
}
table {
	table-layout: fixed;/* fix 해야 함!!! */
}
</style>
<title>Insert title here</title>
</head>
<body>
<u:navbar />

<div class="container-sm">
  <div class="row">
    <table class="table table-hover">
      <thead>
        <tr>
          <th>#번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>수정일</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="board" >
          <tr>
            <td>${board.bno}</td>
            <td class="title">
            	<c:url value="/board/get" var="boardLink">
            		<c:param name="bno" value="${board.bno }" />
            		<c:param name="type" value="${pageMaker.criteria.type }"/>
		    		<c:param name="keyword" value="${pageMaker.criteria.keyword }"/>
            		<c:param name="pageNum" value="${pageMaker.criteria.pageNum }" />
            		<c:param name="amount" value="${pageMaker.criteria.amount }" />
            	</c:url>
            	<a href="${boardLink }"><i><c:out value="${board.title}" /></i></a>
            </td>
            <td><c:out value="${board.writer}" /></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<div id="registerModal" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">등록 완료</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<div class="container-sm mt-3">
  <div class="row justify-content-center">
  	<nav aria-label="Page navigation example">
	  <ul class="pagination">
	  	<c:if test="${pageMaker.prev }">
	  		<c:url value="/board/list" var="prevLink"> <%-- contextRoot 생략 O --%>
	  			<c:param name="type" value="${pageMaker.criteria.type }"/>
		    	<c:param name="keyword" value="${pageMaker.criteria.keyword }"/>
	  			<c:param name="pageNum" value="${pageMaker.startPage-1}" />
	  			<c:param name="amount" value="${pageMaker.criteria.amount}" />
	  		</c:url>
		    <li class="page-item">
 		      <a class="page-link" href="${prevLink }">Previous</a>
		      <%-- <a class="page-link" href="${pageMaker.startPage - 1 }">Previous</a> --%>
		    </li>	  	
	  	</c:if>
	    <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
		    <c:url value="/board/list" var="pageLink">
		    	<c:param name="type" value="${pageMaker.criteria.type }"/>
		    	<c:param name="keyword" value="${pageMaker.criteria.keyword }"/>
		    	<c:param name="pageNum" value="${num }"/>
		    	<c:param name="amount" value="${pageMaker.criteria.amount}"/>
		    </c:url>
		    <li class="page-item ${pageMaker.criteria.pageNum eq num ? 'active' : '' }">
 		    	<a class="page-link" href="${pageLink }">${num }</a>
				<%-- <a class="page-link" href="${num }">${num }</a> --%>
		    </li>	    
	    </c:forEach>
		<c:if test="${pageMaker.next }">
			<c:url value="/board/list" var="nextLink">
				<c:param name="type" value="${pageMaker.criteria.type }"/>
		    	<c:param name="keyword" value="${pageMaker.criteria.keyword }"/>
	  			<c:param name="pageNum" value="${pageMaker.endPage+1}" />
	  			<c:param name="amount" value="${pageMaker.criteria.amount}" />
	  		</c:url>
		    <li class="page-item">
 		      <a class="page-link" href="${nextLink }">Next</a>
		      <%-- <a class="page-link" href="${pageMaker.endPage + 1 }">Next</a> --%>
		    </li>		
		</c:if>
	  </ul>
	</nav>
  </div>
</div>

<div class="d-none">
	<form id="actionForm" action="${root }/board/list">
		<input name="type" value="${pageMaker.criteria.type }" />
		<input name="keyword" value="${pageMaker.criteria.keyword }" />
		<input name="pageNum" value="${pageMaker.criteria.pageNum }" /> <%-- href의 값 --%>
		<input name="amount" value="${pageMaker.criteria.amount }" />
		<input type="submit" />
	</form>
</div>
</body>
</html>