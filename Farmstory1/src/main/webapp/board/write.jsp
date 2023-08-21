<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	// 로그인 여부 확인
	if(sessUser == null){
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101&target=write&group="+group+"&cate="+cate);
		// 여기서 처리 끝내기
		return;
	}
	
	pageContext.include("./_aside"+group+".jsp");
%>
<script>
	$(function(){
		
		$('.btnCancel').click(function(){
			
			const result = confirm('취소하시면 입력하신 내용이 저장되지 않습니다. 그래도 삭제하시겠습니까?');
			
			if(result){
				return true;
			}else{
				return false;
			}
		});
		
	});
</script>
    <section class="write">
        <h3>글쓰기</h3>
        <article>
            <form action="./proc/writeProc.jsp" method="post">
            	<input type="hidden" name="writer" readonly value="<%=sessUser.getUid()%>">
            	<input type="hidden" name="group" readonly value="<%=group%>">
            	<input type="hidden" name="cate" readonly value="<%=cate%>">
                <table>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" maxlength="51" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" required></textarea>                                
                        </td>
                    </tr>
                    <tr>
                        <td>첨부</td>
                        <td><input type="file" name="file"/></td>
                    </tr>
                </table>
                <div>
                    <a href="/Farmstory1/board/list.jsp?group=<%=group %>&cate=<%=cate %>&pg=<%=pg %>" class="btnCancel">취소</a>
                    <input type="submit"  class="btnWrite" value="작성완료">
                </div>
            </form>
        </article>
    </section>
    
   	   		<!-- 내용 끝 -->
   		</article>
	</section>
</div>
<%@include file ="../_footer.jsp" %>