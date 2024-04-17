<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/common.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	input[name="id"]{
	width: 70%;
	}
</style>
</head>
<body>
	<h3>회원가입</h3>
	<hr/>
	<form action="join.do" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td>
					<input type= "text" name="id"/>
					<input type= "button" value="중복체크" onclick="overlay()"/>
				
				</td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type= "text" name="pw"/></td>
			</tr>
			<tr>
				<th>관리자</th>
				<td><input type= "checkbox" name="auth"/>관리자 여부</td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><input type= "text" name="name"/></td>
			</tr>
			<tr>
				<th>AGE</th>
				<td><input type= "text" name="age"/></td>
			</tr>
			<tr>
				<th>GENDER</th>
				<td>
					<input type="radio" name ="gender" value="남"/>남자
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name ="gender" value="여"/>여자
				</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td><input type= "text" name="email"/></td>
			</tr>
			<tr>
				<th colspan="2"><button>회원가입</button></th>
				
			</tr>
			
		</table>
	</form>
	
</body>
<script>
	
	var msg = '${msg}';
	if(msg!=''){
		alert(msg);		
	}
	
	function overlay(){
		var id= $('input[name="id"]').val();
		// ajax를 이용한 비동기 통신
		// ajax에서 XmlHttpRequest 객체를 통해 대신 받아와서 
		// 여기에 뿌려준다.
		$.ajax({
			type:'post',				// method 방식
			url:'overlay.do',			// 요청할 주소
			data:{'id':id},				// 파라메터
			success:function(data){
				console.log(data);
				if(data.use>0){
					alert('이미 사용 중인 아이디입니다.');
					$('input[name="id"]').val('');
				}else{
					alert('사용 가능한 아이디입니다.');
				}				
			},	// 통신 성공 시
			error:function(error){
				console.log(error);
			}		// 통신 실패 시
		});
	}


</script>
</html>