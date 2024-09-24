<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="card-body">
		<form action="insert" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">Title</span>
				<input name="title" class="form-control" placeholder="Title">
			</div>
			<div class="input-group mb-3" id="title_err" style="color:red; display:" >동현이 가자~~~~</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">dueDate</span>
				<input type="date" name="dueDate"><br />
			</div>
			<div class="input-group mb-3" id="dueDate_err" style="color:red; display:none" ></div>
			
			<input type="submit" class="btn btn-primary" value="등록">
			<input type="reset" class="btn btn-secondary" value="초기화">
		</form>
	</div>
<script type="text/javascript">
/*
	const validResult = {};
	
	<c:forEach var="error" items="${errors}">
		validResult['${error.getField()}'] = '${error.defaultMessage}'; 
	</c:forEach>
*/	
<c:forEach var="error" items="${errors}">
	let ${error.getField()}_err = document.getElementById('${error.getField()}_err');
	if (${error.getField()}_err) {
		${error.getField()}_err.style.display = "";
		${error.getField()}_err.innerText = '${error.defaultMessage}';
	}
</c:forEach>
	
	
</script>
</body>
</html>