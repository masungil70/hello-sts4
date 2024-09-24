<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 수정 화면 </h1>
<form action="modify" method="post">
	<div>
		<h3>
			<span>아이디 : </span>
			<input type="text" name="uid" value="${member.uid}" readonly="readonly">
		</h3>
	</div>
	<div>
		<h3>
			<span>비번: </span>
			<input type="password" name="pwd" value="${member.pwd}" >
		</h3>
	</div>
	<div>
		<h3>
			<span>이름: </span>
			<input type="text" name="name" value="${member.name}" >
		</h3>
	</div>
	
	<input type="submit" value="수정">
</form>
</body>
</html>