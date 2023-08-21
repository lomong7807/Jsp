<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@page import="kr.farmstory1.dto.TermsDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file ="../_header.jsp" %>
<script>
	// 창이 모드 로드된 후 아래를 실행한다.
	window.onload = function(){
		
		// name이 chk1과 chk2인 요소들을 가져온다.
		const chk1 = document.getElementsByName('chk1')[0];
		const chk2 = document.getElementsByName('chk2')[0];
		// 클래스가 btnNext인 요소를 가져온다.
		const btnNext = document.querySelector('.btnNext');
		// gtnNext요소에 클릭 이벤트를 등록한다.
		btnNext.addEventListener('click', function(e){
			// 이벤트의 기본 동작을 취소한다. <a> 태그의 링크 클릭 작동을 취소하여 페이지 이동을 막는다.
			e.preventDefault();
			
			// 동의 여부를 확인한다. 
			if(!chk1.checked){
				alert('이용약관에 동의하셔야 합니다.');
				return;
			}else if(!chk2.checked){
				alert('개인정보 취급방침에 동의하셔야 합니다.');
				return;
			}else{
				// 위의 이용약관과 개인정보 취급방침에 모두 동의한 경우에만 다음 페이지로 이동하도록 한다.
				location.href = '/Farmstory1/user/register.jsp';
			}    			    			
		});
	}


</script>
<%
	TermsDTO terms = UserDAO.getInstance().selectTerms();
%>
<div id="user">
<section class="terms">
    <table>
        <caption>사이트 이용약관</caption>
        <tr>
            <td>
                <textarea readonly><%= terms.getTerms() %></textarea>
                <p>
                    <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <table>
        <caption>개인정보 취급방침</caption>
        <tr>
            <td>
                <textarea readonly><%= terms.getPrivacy() %></textarea>
                <p>
                    <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <div>
        <a href="/Farmstory1/user/login.jsp" class="btnCancel">취소</a>
        <a href="#" class="btnNext">다음</a>
    </div>
</section>
</div>
<%@include file ="../_footer.jsp" %>
