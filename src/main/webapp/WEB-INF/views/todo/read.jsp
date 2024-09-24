<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>할일 상세보기</h1>
<div>
	<h3>
		<span>아이디 : </span>
		<span>${todo.id}</span>
	</h3>
</div>
<div>
	<h3>
		<span>제목: </span>
		<span>${todo.title}</span>
	</h3>
</div>
<div>
	<h3> 
		<span>날짜: </span>
		<span>${todo.dueDate}</span>
	</h3>
</div>
<div>
	<h3>
		<span>종료여부: </span>
		<span>${todo.finishedStr}</span>
	</h3>
</div>

<a href="modify?id=${param.id}&${pageRequestDTO.link}">수정</a>
<a href="remove?id=${param.id}&${pageRequestDTO.link}">삭제</a>
<a href="list?&${pageRequestDTO.link}">목록</a>
</body>
</html>