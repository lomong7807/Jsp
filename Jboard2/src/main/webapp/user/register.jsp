<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp"%>
<script>
	window.onload = function(){
		
		const btnCheckUid = document.getElementById('btnCheckUid');
		const inputUid = document.getElementsByName('uid')[0];
		const uidResult = document.getElementsByClassName('uidResult')[0];
		
		btnCheckUid.onclick = function(){
			
			// 순수 js AJAX 객체
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/Jboard2/user/checkUid.do?uid='+inputUid.value);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == XMLHttpRequest.DONE){
					
					if(xhr.status == 200){
						
						const data = JSON.parse(xhr.response);
						
						if(data.result > 0){
							uidResult.innerText = '이미 사용중인 아이디 입니다.';
							uidResult.style.color = 'red';
						}else{
							uidResult.innerText = '사용 가능한 아이디 입니다.';
							uidResult.style.color = 'green';
						}
					}
				} // readystate end
			}; //onreadystatechange end
		};// onclick end
		
		$('#btnCheckNick').click(function(){
			
			const nick = $('input[name=nick]').val();
			
			$.ajax({
				url:'/Jboard2/user/checkNick.do?nick='+nick,
				type:'get',
				dataType:'json',
				success: function(data){
					
					if(data.result > 0){
						$('.nickResult').css('color','red').text('이미 사용중인 별명입니다.');
					}else{
						$('.nickResult').css('color','green').text('사용 가능한 별명입니다.');
					}
				}
			}); //ajax end
		});// btnCheckNick end
		
		$('input[name=hp]').focusout(function(){
			const hp = $(this).val();
			const url = '/Jboard2/user/checkHp.do?hp='+hp;
			
			$.get(url, function(result){
				
				const data = JSON.parse(result);
				console.log(data);
				if(data.result > 0){
					$('.resultHp').css('color','red').text('이미 사용중인 휴대폰번호입니다.');
				}else{
					$('.resultHp').css('color','green').text('사용 가능한 휴대폰번호입니다.');
				}
				
			});
		});
		
	}; // window end
	
	
	// 이메일 인증
	$(function(){
		
		let preventDoubleClick = false;
		
		$('#btnEmailCode').click(function(){
			
			const email = $('input[name=email]').val();
			const jsonData = {
				"email" : email
			};
			
			if(preventDoubleClick){
				return;
			}
			
			preventDoubleClick = true;
			$('.resultEmail').css('color', 'black').text('인증코드 전송 중 입니다. 잠시만 기다려주세요...');
			
			setTimeout(function(){
			
				$.ajax({
					url:'/Jboard2/user/authEmail.do',
					type:'GET',
					data:jsonData,
					dataType:'json',
					success:function(data){
						if(data.status> 0){
							$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
						}else{
							$('.resultEmail').css('color', 'red').text('인증코드 전송이 실패했습니다. 잠시후 다시 하십시오.');
						}
						preventDoubleClick = false;
					}
				});
			}, 1000);
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
					}else{
						$('.resultEmail').css('color', 'red').text('이메일 인증이 실패 했습니다. 다시 시도하십시오.');
					}
				}
			});
		}); // EmailAuth end
	});// 이메일 인증 end
	
</script>
<main id="user">
    <section class="register">

        <form action="/Jboard2/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                        <span class="uidResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="pass1" placeholder="비밀번호 입력"/></td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"/></td>
                </tr>
            </table>

            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>                        
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <button type="button" id="btnCheckNick"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                        <span class="nickResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/>
                        <button type="button" id="btnEmailCode"><img src="../img/chk_auth.gif" alt="인증번호 받기"/></button>
                        <span class="resultEmail"></span>
                        <div class="auth">
                            <input type="text" name="auth" placeholder="인증번호 입력"/>
                            <button type="button" id="btnEmailAuth"><img src="../img/chk_confirm.gif" alt="확인"/></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td><input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13"/>
                    	<span class="resultHp"></span>
                    </td>
                   		
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <input type="text" name="zip" placeholder="우편번호" readonly/>
                        <button type="button" class="btnZip" onclick="zipcode()"><img src="../img/chk_post.gif" alt="우편번호찾기"/></button>
                        <input type="text" name="addr1" placeholder="주소 검색" readonly/>
                        <input type="text" name="addr2" placeholder="상세주소 입력"/>
                    </td>
                </tr>
            </table>

            <div>
                <a href="./login.html" class="btn btnCancel">취소</a>
                <input type="submit" value="회원가입" class="btn btnRegister"/>
            </div>

        </form>

    </section>
</main>
<%@ include file="./_footer.jsp"%>