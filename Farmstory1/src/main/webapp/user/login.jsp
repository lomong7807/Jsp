<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String target = request.getParameter("target");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no  = request.getParameter("no");
%>
<!-- 
<script src="https://developers.kakao.com/sdk/js/kakao/js"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
    Kakao.init('b88c9dca180752d95b07980948624731');
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                        alert(JSON.stringify(response))
                    },
                    fail: function (error) {
                        alert(JSON.stringify(error))
                    },
                })
            },
            fail: function (error) {
                alert(JSON.stringify(error))
            },
        })
    }
</script>
 -->
<div id="user">
     <section class="login">
         <form action="/Farmstory1/user/loginProc.jsp" method="post">
         	<input type="hidden" name="target" value="<%=target%>"/>
         	<input type="hidden" name="group" value="<%=group%>"/>
         	<input type="hidden" name="cate" value="<%=cate%>"/>
         	<input type="hidden" name="no" value="<%=no%>"/>
             <table border="0">
                 <tr>
                     <td><img src="/Farmstory1/images/login_ico_id.png" alt="아이디"></td>
                     <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                 </tr>
                 <tr>
                     <td><img src="/Farmstory1/images/login_ico_pw.png" alt="비밀번호"></td>
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
             <a href="/Farmstory1/user/terms.jsp">회원가입</a>
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
<%@include file="../_footer.jsp"%>