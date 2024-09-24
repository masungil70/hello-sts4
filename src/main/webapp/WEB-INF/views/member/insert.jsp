<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="insert"  method="post">
		<div>
			<h3>
				<span>아이디 : </span>
				<input type="text" name="uid" v>
			</h3>
		</div>
		<div>
			<h3>
				<span>비번: </span>
				<input type="password" name="pwd"  >
			</h3>
		</div>
		<div>
			<h3>
				<span>확인비번: </span>
				<input type="password" name="pwd2" id="pwd2" >
			</h3>
		</div>
		<div>
			<h3>
				<span>이름: </span>
				<input type="text" name="name"  >
			</h3>
		</div>

		<input type="submit" value="등록">
	</form>
</body>
</html>