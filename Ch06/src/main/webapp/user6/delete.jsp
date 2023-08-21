<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");
%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>user6::delete</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		
		<script>
			$(function(){
				
				//삭제데이터 요청하기
				$.get("./User.jsp?uid=<%=uid%>", function(data){
					console.log(data);
					
					$('input[name=uid]').val(data.uid);
				});
				
				$('input[type=submit]').click(function(e){
					e.preventDefault();
					
					const uid = $('input[name=uid]').val();
					
					const jsonData = {
						"uid" : uid
					};
					
					console.log(jsonData);
					
					$.post('./deleteProc.jsp', jsonData, function(data){
						
						if(data.result >= 1){
							alert('데이터가 삭제 되었습니다.');
							location.href='./list.jsp';
						}else{
							alert('데이터 삭제에 실패했습니다.');
						}
					});
				})
			});
		</script>
	</head>
	<body>
		<h3>User6 삭제</h3>
		<a href='/Ch06/user6/list.jsp'>취소</a>
		<form action="#" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					
					<input type="submit" value="삭제"></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>