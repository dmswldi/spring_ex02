<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg  navbar-dark bg-dark container-sm mb-3">
  <a class="navbar-brand" href="#">게시판</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
      	<c:url value="/board/list" var="listLink">
			<c:param value="${criteria.pageNum }" name="pageNum" />      	
			<c:param value="${criteria.amount }" name="amount" />      	
      	</c:url>
        <a class="nav-link" href="${listLink }">목록 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
      	<c:url value="/board/register" var="registerLink">
			<c:param value="${criteria.pageNum }" name="pageNum" />      	
			<c:param value="${criteria.amount }" name="amount" />      	
      	</c:url>
        <a class="nav-link" href="${registerLink }">글쓰기</a>
      </li>
    </ul>
  </div>
</nav>