/**
 * 사용자 개인정보 중복체크
 */

$(function(){
	//아이디 중복체크
	$('#btnCheckUid').click(function(){
		// 아래에서 name이 uid인것의 값을 가져온다.
		const uid = $('input[name=uid]').val();
		
		// match = 부합하면 / script끼리 모두 공유
		if(!uid.match(reUid)){
			$('.resultId').css('color','red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return;
		}
		
		
		const jsonData = {
			"uid": uid	
		};
		$.ajax({
			url:'/Farmstory2/user/checkUid.do',
			type:'GET',
			data: jsonData,
			dataType:'json',
			success:function(data){
				console.log(data.result);
				if(data.result >= 1){
					$('.resultId').css('color','red').text('이미 사용중인 아이디 입니다.');
					isUidOk = false;
				}else{
					$('.resultId').css('color','green').text('사용 가능한 아이디 입니다.');
					isUidOk = true;
				}
			}
		});
	}); // 아이디 중복체크 끝
	
	// 닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		
		// 입력 데이터 가져오기
		const nick = $(this).val();
		
		if(!nick.match(reNick)){
			$('.resultNick').css('color','red').text('유효한 닉네임이 아닙니다.');
			isNickOk = false;
			return;
		}
		
		console.log(nick);
		
		// JSON 생성
		const jsonData = {
			"nick": nick	
		};
		// url:'./checkNick.jsp?nick='+nick
		// 데이터 전송
		$.get('/Farmstory2/user/checkNick.do', jsonData, function(data){
			// get으로 받은거라서 ajax로 바꿔야됨.
			const dataParsed = JSON.parse(data);
			
			console.log(dataParsed.result);
			console.log(dataParsed);
			if(dataParsed.result >= 1){
				$('.resultNick').css('color','red').text('이미 사용중인 닉네임 입니다.');
				isNickOk = false;
			}else{
				$('.resultNick').css('color','green').text('사용 가능한 닉네임 입니다.');
				isNickOk = true;
			}
		});
	});// 닉네임 중복체크 끝
	
	// 이메일 중복체크
	const email = document.getElementsByName('email')[0];
	
	email.onfocusout = function(){
		
		const resultEmail = document.getElementById('resultEmail');
		
		// 입력 데이터 가져오기
		const email = this.value;
		
		
		
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일 아닙니다.';
			resultEmail.style.color = 'red';
			isEmailOk = false;
			return;
		}
		
		
		console.log('email : '+ email);
		
		// 데이터 전송
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/Farmstory2/user/checkEmail.do?email='+email);
		xhr.send();
		
		// 응답 결과
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE){
				if(xhr.status == 200){
					const data = JSON.parse(xhr.response);
					console.log('data : '+ data);
					
					
					if(data.result >= 1){
						//alert('이미 사용중인 이메일 입니다.');
						resultEmail.innerText = '이미 사용중인 이메일 입니다.';
						resultEmail.style.color = 'red';
						isEmailOk = false;
					}else{
						//alert('사용 가능한 이메일 입니다.');
						resultEmail.innerText = '사용 가능한 이메일 입니다.';
						resultEmail.style.color = 'green';
						isEmailOk = true;
					}
				}
			}
		}//onreadystatechance end
	}
	// 휴대폰 중복체크
	document.getElementsByName('hp')[0].addEventListener('focusout', function(){
		
		const resultHp = document.getElementById('resultHp');
		const hp = this.value;
		
		if(!hp.match(reHp)){
			resultHp.innerText = '유효한 휴대폰번호가 아닙니다.';
			resultHp.style.color = 'red';
			isHpOk = false;
			// 정규표현식에 부합한 내용만 서버로 날린다. 부합하지 않을때는 아래로 내려갈 필요가 없다.
			return;
		}
		
		const url = '/Farmstory2/user/checkHp.do?hp='+this.value;
		
		fetch(url)
			.then(response => response.json())
			.then(data => {
				console.log(data);
				
				if(data.result >= 1){
					resultHp.innerText = '이미 사용중인 휴대폰번호 입니다.';
					resultHp.style.color = 'red';
					isHpOk = false;
				}else{
					resultHp.innerText = '사용 가능한 휴대폰번호 입니다.';
					resultHp.style.color = 'green';
					isHpOk = true;
				}
			});
		
	}); // 휴대폰 중복체크 끝
});	//사용자 개인정보 중복체크 끝