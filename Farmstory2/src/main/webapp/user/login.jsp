<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<div id="user">
    <section class="login">
         <form action="${ctxPath}/user/login.do" method="post">
         	<input type="hidden" name="target" value=""/>
         	<input type="hidden" name="group" value=""/>
         	<input type="hidden" name="cate" value=""/>
         	<input type="hidden" name="no" value=""/>
			<table border="0">
                 <tr>
                     <td><img src="/Farmstory2/images/login_ico_id.png" alt="아이디"></td>
                     <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                 </tr>
                 <tr>
                     <td><img src="/Farmstory2/images/login_ico_pw.png" alt="비밀번호"></td>
                     <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                 </tr>
             </table>
             <input type="submit" value="로그인" class="btnLogin">
         </form>
         <div>
             <h3>회원 로그인 안내</h3>
             <p>
                 아직 회원이 아니시면 회원으로 가입하세요.
             </p>
             <a href="${ctxPath}/user/terms.do">회원가입</a>
         	<div>
    <!-- 
    <h1>로그인</h1>
    <a href="javascript:kakaoLogin()"><img src="/Farmstory1/src/main/webapp/images/kakao_login_small.png"/></a>
    <button>네이버 로그인</button>
     -->
			</div>
         </div>
     </section>
</div>
<%@include file ="../_footer.jsp" %>

<!--              <ul class="login_wrap">
                 <li class="login_item" style="display: block;">
                 	<div class="login_inner">
                 		<div class="id_pw_wrap">
                 			<div class="input_row" id="id_line">
                 				<div class="icon_cell" id="id_cell">  
                 					<span class="icon_id">
                 						<img src="/Farmstory2/images/login_ico_id.png" alt="아이디">
                 					</span>
                 					<input type="text" id="id" name="id" placeholder="아이디" title="아이디 입력" class="input_text" maxlength="41" alt="아이디">
                 				</div>
                 			</div>
                 			<div class="input_row" id="pw_line">
                 				<div class="icon_cell" id="pw_cell">  
                 					<span class="icon_pw">
                 						<img src="/Farmstory2/images/login_ico_pw.png" alt="아이디">
                 					</span>
                 					<input type="text" id="pw" name="pw" placeholder="비밀번호" title="비밀번호 입력" class="input_text" maxlength="16" alt="비밀번호">
                 				</div>
                 			</div>
                 		</div>
                 	</div>
                 </li>
             </ul> -->