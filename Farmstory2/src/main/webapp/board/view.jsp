<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
<script>
	$(function(){
		
		// 댓글 삭제(동적 생성 이벤트 구현)
		// 페이지에서 작성된 댓글에 대한 삭제 이벤트는 구현되지 않기때문에 동적 생성 이벤트로 구현해야 한다.
		$(document).on('click', '.removeComment', function(e){
			e.preventDefault();
			alert('삭제');
			
			const no = $(this).data('no');
			const parent = $(this).data('parent');
			const article = $(this).parent().parent();
			
			console.log('no : '+ no);
			
			const jsonData ={
					"kind": "REMOVE",
					"no" : no,
					"parent " : parent
			}
			
			if(confirm('정말 삭제하시겠습니까?') == true){
				$.ajax({
					url: '/Farmstory2/comment.do',
					type: 'get',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						
						// 삭제에 성공하면
						if(data.result > 0){
							alert('댓글이 삭제되었습니다.');
							
							// 화면처리
							article.remove();
						}
					}
				}); //ajax end
			} // confirm
		})//removeComment end
		
		
		$('#btnComment').click(function(e){
			e.preventDefault();
			alert('click');
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=commentContent]').val();
			const writer = $('#formComment > input[name=writer]').val();
			
			console.log("content : "+content);
			
			const jsonData = {
					"parent" : parent,
					"content" : content,
					"writer" : writer
			};
			
			console.log('jsonData : '+jsonData);
			
			$.ajax({
				url: '/Farmstory2/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					if(data.result > 0){
						
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2, 4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year + "-"+month+"-"+date;
						
						const article = `<article>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>`+now+`</span>
											<p class='content'>`+content+`</p>
											<div>
												<a href='#' class='remove'>삭제</a>
												<a href='#' class='modify'>수정</a>
											</div>
										</article>`;
										
						$('.commentList').append(article);
						$('#formComment > textarea[name=commentContent]').val('');
						
						const empty = $('.empty');
						
						if(empty.is(':visible')){
							empty.remove();
						}
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			}); //ajax end
		})// btnComment click end
	})//end
</script>
    <section class="view">
        <h3>글보기</h3>
        <input type="text" name="writer" readonly value="작성자 : ${article.nick}">
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="title" value="${article.title}" readonly/></td>
            </tr>
            <tr>
                <td>첨부파일</td>
                <td>
                    <a href="${ctxPath}/fileDownload.do?fno=${file.fno}">${file.ofile}</a>
                    <span> | 다운로드 수 [${file.download}]</span>
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea name="content" readonly>${article.content}</textarea>
                </td>
            </tr>
        </table>
        <div>
            <a href="${ctxPath}/delete.do?group=${group}&cate=${cate}&no=${article.no}" class="btnDelete">삭제</a>
            <a href="${ctxPath}/board/modify.do?group=${group}&cate=${cate}&no=${article.no}" class="btnModify">수정</a>
            <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <c:forEach items="${comments}" var="comment">  
            <article class="comment">
	            	<input type="hidden" name="no" value="${comment.no}">
	            	<input type="hidden" name="parent" value="${comment.parent}">
	                <span>
	                    <span class="nick">${comment.nick}</span>
	                    <span class="date">${comment.rdate}</span>
	                </span>
	                <textarea name="comment" readonly>${comment.content}</textarea>
	                
	                <!-- 현재 이용자가 댓글 작성자와 동일한지 확인하여 삭제와 수정을 보이게끔 한다. -->
	                <div>
	                	<!-- for문 안에서 반복되기 때문에 id가 반복되면 안돼서 class로 사용 -->
	                    <a href="#" data-no="${comment.no}" data-parent="${comment.parent}" class="removeComment">삭제</a>
	                    
	                    <a href="${ctxPath}/board/view.jsp" class="can">취소</a>
	                    <a href="#" class="mod">수정</a> <!-- AJAX 처리해야됨 -->
	                </div>
                </form>
            </article>
            </c:forEach>
            
           	<!-- 등록된 댓글이 없다면 아래를 출력 -->
           	<c:if test="${comments.size()==0}">
           	<p class="empty">등록된 댓글이 없습니다.</p>
           	</c:if>
        </section>

        <!-- 댓글입력폼 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="#" id="formComment" method="post">
            	<input type="hidden" name="group" value="${group}"/>
            	<input type="hidden" name="cate" value="${cate}"/>
            	<input type="hidden" name="parent" value="${article.no}"/>
            	<input type="hidden" name="writer" value="${sessUser.uid}"/>
                <textarea name="commentContent" required></textarea>
                <div>
                    <a href="#" class="btnCancel">취소</a>
                    <input type="submit" id="btnComment" class="btnWrite" value="작성완료"/>
                </div>
            </form>
        </section>
    </section>
    	   		<!-- 내용 끝 -->
   		</article>
	</section>
</div>
<%@include file ="../_footer.jsp" %>