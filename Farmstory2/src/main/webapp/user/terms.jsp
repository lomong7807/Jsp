<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(function(){
		
		const chk1 = $('.chk1');
		const chk2 = $('.chk2');
		
		
		$('.btnNext').click(function(e){
			e.preventDefault();
			if(!chk1.is(':checked')){
				alert('이용약관에 동의하셔야 합니다.');
				return;
			}else if(!chk2.is(':checked')){
				alert('개인정보 취급방침에 동의하셔야 합니다.');
				return;
			}else{
				location.href = '/Farmstory2/user/register.do';
			}	
		}); // btnNext click end
	});//end
</script>

<div id="user">
<section class="terms">
    <table border="1">
        <caption>사이트 이용약관</caption>
        <tr>
            <td>
                <textarea readonly>${dto.terms}</textarea>
                <p>
                    <label><input type="checkbox" name="chk1" class="chk1"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <table>
        <caption>개인정보 취급방침</caption>
        <tr>
            <td>
                <textarea readonly>${dto.privacy}</textarea>
                <p>
                    <label><input type="checkbox" name="chk2" class="chk2"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <div>
        <a href="/Farmstory2/user/login.jsp" class="btnCancel">취소</a>
        <a href="#" class="btnNext">다음</a>
    </div>
</section>
</div>
<%@include file ="../_footer.jsp" %>