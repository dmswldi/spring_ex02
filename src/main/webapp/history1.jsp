<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	if(history.state != null){
		console.log("state가 있다");
	}
	/* history.state == null */
	history.replaceState({}, null, null);/* {abc: 'def'} */
	/* history.state == {} */
	
</script>
</head>
<body>
<h1>history 1</h1>
<a href="history2.jsp">history2로 가기</a>
</body>
</html>