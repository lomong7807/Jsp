/**
 * 
 */
$(function(){
	/////////////////////////////// 아이디 중복 체크 ///////////////////////////////
	const btnCheckUid = document.getElementById('btnCheckUid');
	const inputUid = document.getElementsByName('uid')[0];
	const uidResult = document.getElementsByClassName('uidResult')[0];
	
	// myInfo를 실행할때 uid값이 null이기 때문에 예외처리
	if(btnCheckUid != null){
		// btnCheckUid를 클릭하면 다음을 실행
		btnCheckUid.onclick = function(){
			// inputUid에 입력된 값을 uid라고 한다.
			const uid = inputUid.value;
			// 아이디 유효성 검사
			// 미리 걸러서 서버의 부담을 줄이기 위해 아이디 유효성 검사를 제일 위에서 한다.
			// uid가 정규표현식인 reUid의 형식과 일치하는지 확인한다.
			if(!uid.match(reUid)){
				// 일치하지 않는다면 아래를 출력하고 return으로 돌아가게끔 한다.
				uidResult.innerText = '유효한 아이디가 아닙니다.';
				uidResult.style.color = 'red';
				isUidOk = false;
				return;
			}						
			// 순수 js AJAX 객체
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/Jboard2/user/checkUid.do?uid='+uid);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == XMLHttpRequest.DONE){
					
					if(xhr.status == 200){
						
						const data = JSON.parse(xhr.response);
						
						if(data.result > 0){
							uidResult.innerText = '이미 사용중인 아이디 입니다.';
							uidResult.style.color = 'red';
							isUidOk = false;
						}else{
							uidResult.innerText = '사용 가능한 아이디 입니다.';
							uidResult.style.color = 'green';
							isUidOk = true;
							
						}
					}
				} // readystate end
			}; //onreadystatechange end
		};// onclick end
	}
	
	
	
	
	/////////////////////////////// 닉네임 중복 체크 ///////////////////////////////
	$('#btnCheckNick').click(function(){
		
		console.log('here nick...1');
		const nick = $('input[name=nick]').val();
		console.log('here nick...2');
		
		// 별명 유효성 검사
		if(!nick.match(reNick)){
			$('.nickResult').css('color','red').text('유효한 별명이 아닙니다.');
			isNickOk = false;
			return;
		}	
		console.log('here nick...3');
		$.ajax({
			url:'/Jboard2/user/checkNick.do?nick='+nick,
			type:'get',
			dataType:'json',
			success: function(data){
				
				if(data.result > 0){
					$('.nickResult').css('color','red').text('이미 사용중인 별명입니다.');
					isNickOk = false;
				}else{
					$('.nickResult').css('color','green').text('사용 가능한 별명입니다.');
					isNickOk = true;
				}
			}
		}); //ajax end
	});// btnCheckNick end
	
	
	/////////////////////////////// 휴대폰 중복 체크 ///////////////////////////////
	$('input[name=hp]').focusout(function(){
		const hp = $(this).val();
		const url = '/Jboard2/user/checkHp.do?hp='+hp;
		
		// 휴대폰 유효성 검사
		if(!hp.match(reHp)){
			$('.resultHp').css('color','red').text('유효한 휴대폰 번호가 아닙니다.');
			isHpOk = false;
			return;
		}	
		
		$.get(url, function(result){
			
			const data = JSON.parse(result);
			console.log(data);
			if(data.result > 0){
				$('.resultHp').css('color','red').text('이미 사용중인 휴대폰번호입니다.');
				isHpOk = false;
			}else{
				$('.resultHp').css('color','green').text('사용 가능한 휴대폰번호입니다.');
				isHpOk = true;
			}
		});
	});
	
}); // onload end
