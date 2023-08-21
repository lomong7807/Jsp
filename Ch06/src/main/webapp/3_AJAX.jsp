<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>3_AJAX</title>
		<!-- 
			날짜 : 2023/07/31
			이름 : 강윤수
			내용 : JSP AJAX 실습하기
		 -->
		 <script>
		 	window.onload = function(){

		 		const btn1 = document.getElementById("btn1");
		 		const spans = document.getElementsByTagName('span');
		 		
		 		btn1.onclick = function(){
					/*
					XMLHttpRequest(XHR)객체는 웹 페이지와 서버 간에 데이터를 비동기적으로 교환하기 위해 사용되는
					Javascript의 핵심적인 객체이다. 이 객체를 사용하여 AJAX 요청을 수행하고 서버로부터 데이터를 가져오거나
					서버에 데이터를 보낼 수 있다.
					*/
		 			const xhr = new XMLHttpRequest();
					// XMLHttpRequest 객체를 사용해 json1에 GET 요청을 보낸다.
		 			xhr.open('GET', '/Ch06/data/json1.jsp');
		 			xhr.send();
		 			
		 			/*
		 			서버로부터의 응답을 처리하기 위해 'onreadystatechange'이벤트 핸들러를 등록한다.
		 			이벤트 핸들러를 등록한다는 것은 해당 이벤트가 발생했을 때 실행할 코드를 지정하는 과정이다.
		 			*/
		 			xhr.onreadystatechange = function(){
		 				
		 				// 서버로부터의 응답이 완료되었을 때 응답 상태 코드를 확인한다.
		 				if(xhr.readyState == XMLHttpRequest.DONE){
		 					// 200이라는 뜻은 성공적인 응답을 의미한다.
		 					if(xhr.status == 200){
		 						
		 						// 응답이 성공적인 경우, JSON 형식으로 데이터를 받는다.
		 						const data = JSON.parse(xhr.response);
								console.log('data : '+data);		 						
		 						
								// 서버에서 받은 JSON 데이터의 내용을 span 요소들에 표시한다.
		 						spans[0].innerText = data.uid;
		 						spans[1].innerText = data.name;
		 						spans[2].innerText = data.hp;
		 						spans[3].innerText = data.age;
		 						
		 					}
		 					
		 				}
		 				
		 			}// onreadystatechange end
							 			
		 		}// btn1 onclick end
		 		
		 		const table = document.getElementsByTagName('table')[0];
		 		const btn2 = document.getElementById("btn2");
		 		btn2.addEventListener('click', function(){
		 			//alert('클릭');
		 			
		 			/*
		 			fetch함수는 네트워크를 통해 리소스를 가져오기 위해 사용되는 javaScript의 기본 내장 함수다.
		 			웹 개발에서 주로 서버와의 데이터 통신을 수행하는 데에 사용되며, 비동기적으로 데이터를 요청하고 응답을 받아오는 데 적합하다.
		 			*/
		 			fetch('/Ch06/data/json2.jsp')
		 				.then((response)=>{
		 					console.log(response);
		 					return response.json();
		 				})
		 				.then((data)=>{
		 					console.log(data);
		 					//JSON 데이터를 받아서 테이블을 동적으로 생성
		 					for(let user of data){
		 						console.log(user);
		 						const tr = document.createElement('tr');
		 						const td1 = document.createElement('td');
		 						const td2 = document.createElement('td');
		 						const td3 = document.createElement('td');
		 						const td4 = document.createElement('td');
		 						//JSON 데이터에서 각 사용자의 정보를 가져와서 각 셀에 할당
		 						td1.innerText = user.uid;
		 						td2.innerText = user.name;
		 						td3.innerText = user.hp;
		 						td4.innerText = user.age;
		 						// 생성한 셀들을 테이블의 한 행으로 추가
		 						tr.appendChild(td1);
		 						tr.appendChild(td2);
		 						tr.appendChild(td3);
		 						tr.appendChild(td4);
		 						// 테이블에 새로운 행을 추가
		 						table.appendChild(tr);
		 					}
		 					
		 				}); //fetch end
		 			
		 		}); //addEventListenter end
		 		
		 		
		 		
		 	};// window onload end
		 </script>
		 
	</head>
	<body>
		<h3>3.AJAX 실습하기</h3>
		
		<h4>JSON/XML 실습</h4>
		<a href="./data/json1.jsp">JSON1 데이터 생성</a>
		<a href="./data/json2.jsp">JSON2 데이터 생성</a>
		<a href="./data/xml1.jsp">XML1 데이터 생성</a>
		<a href="./data/xml2.jsp">XML2 데이터 생성</a>
		
		<h4>AJAX 실습</h4>
		<button id="btn1">데이터 요청</button>
		
		<p>
			아이디 : <span></span><br>
			이름 : <span></span><br>
			휴대폰 : <span></span><br>
			나이 : <span></span><br>
		</p>
		
		<button id="btn2">데이터 요청</button>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		
		
	</body>
</html>