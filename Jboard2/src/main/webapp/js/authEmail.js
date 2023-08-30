/**
 * 
 */

 // 이메일 인증
$(function(){
	
	let preventDoubleClick = false;
	
	$('#btnEmailCode').click(function(){
		
		const type  = $('input[name=type]').val();
		const uid  = $('input[name=uid]').val();
		const name  = $('input[name=name]').val();
		const email = $('input[name=email]').val();
		
		console.log('uid : ' + uid);
		console.log('name : ' + name);
		console.log('email : ' + email);
		
		const jsonData = {
			"type" : type,
			"uid" : uid,
			"name" : name,
			"email" : email
		};
		console.log("jsonData :"+jsonData);
		
		if(preventDoubleClick){
			return;
		}
		
		preventDoubleClick = true;
		$('.resultEmail').css('color', 'black').text('인증코드 전송 중 입니다. 잠시만 기다려주세요...');
		$('.resultEmailForId').css('color', 'black').text('인증코드 전송 중 입니다. 잠시만 기다려주세요...');
		$('.resultEmailForPass').css('color', 'black').text('인증코드 전송 중 입니다. 잠시만 기다려주세요...');
		
		console.log('jsonData : ' + jsonData);
		
		setTimeout(function(){
		
			$.ajax({
				url:'/Jboard2/user/authEmail.do',
				type:'GET',
				data:jsonData,
				dataType:'json',
				success:function(data){
					console.log(data);
					// result = 중복된 이메일이 있는지 확인
					// status = 메일 발송이 성공적으로 됐는지 확인
					
					// 중복된 메일이 있다면
					if(data.result > 0){						
						
						console.log('here1');
						
						$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일 입니다.');
						isEmailOk = false;
						
						// 중복된 메일이 없고 성공적으로 메일이 발송 됐다면
						if(data.status > 0){
							console.log('here2');
							$('.resultEmailForId').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').prop('disabled', false);
						
						// 중복된 메일은 없지만 메일 발송에 실패했다면
						}else{
							console.log('here3');
							$('.resultEmailForId').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시요.');
							$('.resultEmailForPass').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시요.');
						}
						
				}else{
					console.log('here4');
						// 메일 발송이 성공적으로 됐다면
						if(data.status> 0){
							console.log('here5');
							$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
							/* 인증번호 받은 후 수정 불가하게끔 */
							$('input[name=email]').attr('readonly',true);
						
						// 메일 발송에 실패했다면
						}else{
							console.log('here6');
							$('.resultEmail').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.');
							$('.resultEmailForId').css('color', 'red').text('해당하는 사용자, 이메일이 일치하지 않습니다.');
							$('.resultEmailForPass').css('color', 'red').text('해당하는 사용자, 이메일이 일치하지 않습니다.');
						}	
					}
					preventDoubleClick = false;
				}
			}); // ajax end
		}, 1000); // settimeout end
	}); // EmailCode end
	
	$('#btnEmailAuth').click(function(){
		
		const code = $('input[name=auth]').val();
		const jsonData = {
				"code":code
		};
		
		$.ajax({
			url:'/Jboard2/user/authEmail.do',
			type:'POST',
			data:jsonData,
			dataType:'json',
			success: function(data){
				if(data.result > 0){
					$('.resultEmail').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForId').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForPass').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color', 'red').text('이메일 인증이 실패 했습니다. 다시 시도하십시오.');
					$('.resultEmailForId').css('color', 'red').text('이메일 인증이 실패 했습니다. 다시 시도하십시오.');
					$('.resultEmailForPass').css('color', 'red').text('이메일 인증이 실패 했습니다. 다시 시도하십시오.');
					$('input[name=email]').attr('readonly',false);
					isEmailOk = false;
				}
			}
		});
	}); // EmailAuth end
});// 이메일 인증 end