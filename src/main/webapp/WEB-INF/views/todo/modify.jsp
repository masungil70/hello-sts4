<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>할일 수정 화면 </h1>
<form action="modify?${pageRequestDTO.link}" method="post">
	<div>
		<h3>
			<span>아이디 : </span>
			<input type="text" name="id" value="${todo.id}" readonly="readonly">
		</h3>
	</div>
	<div>
		<h3>
			<span>제목: </span>
			<input type="text" name="title" value="${todo.title}" >
		</h3>
	</div>
	<div>
		<h3>
			<span>날짜: </span>
			<input type="date" name="dueDate" value="${todo.dueDate}" >
		</h3>
	</div>
	<div>
		<h3>
			<span>종료여부: </span>
			<input type="radio" id="finished1" name="finished" value="true" ${todo.finished ? "checked='checked'" : ""} > <label for="finished1">완료</label> 
			<input type="radio" id="finished2" name="finished" value="false" ${!todo.finished ? "checked='checked'" : ""}> <label for="finished2">진행중</label> 
			
		</h3>
	</div>
	
	<input type="submit" value="수정">
</form>
</body>
</html>