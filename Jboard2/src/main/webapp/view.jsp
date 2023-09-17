<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./_header.jsp"%>
<script>
	$(function(){
		
		// 댓글 삭제(동적 생성 이벤트 구현)
		// 페이지에서 작성된 댓글에 대한 삭제 이벤트는 구현되지 않기때문에 동적 생성 이벤트로 구현해야한다.
		$(document).on('click','.remove', function(e){
			e.preventDefault();
			//alert('삭제 클릭');
			const no = $(this).data('no');
			const parent = $(this).data('parent');
			const article = $(this).parent().parent(); 
			
			console.log('no : '+ no);
			
			const jsonData = {
					"kind": "REMOVE",
					"no" : no,
					"parent" : parent
			}
			if(confirm('정말 삭제하시겠습니까?') == true){
				$.ajax({
					url: '/Jboard2/comment.do',
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
		});	// 댓글 삭제 end	
		
		// 댓글 입력
		$('#btnComment').click(function(e){
			e.preventDefault();
			
			if($('#formComment > textarea[name=commentContent]').val().length === 0){
				alert('내용을 입력하세요.');
				return;
			}
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=commentContent]').val();
			const writer = $('#formComment > input[name=writer]').val();
			//const no = ;
			//const parent = ;
			
			console.log("content : "+content);
			
			const jsonData = {
					"parent" : parent,
					"content" : content,
					"writer" : writer
			};
			
			console.log('jsonData : '+ jsonData);
			
			$.ajax({
				url: '/Jboard2/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					if(data.result > 0){
						//alert('댓글이 등록 되었습니다.');
						
						// 동적 화면 생성
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
						// 등록된 댓글이 없으면 보이게끔 함
						if(empty.is(':visible')){
							empty.remove();
						}
						
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			});// ajax end
		}); // 댓글 등록 btnComment click end
	}); // end
</script>

<main id="board">
    <section class="view">
        
        <table border="0">
            <caption>글보기</caption>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${article.title}" readonly/></td>
            </tr>
            <tr>
                <th>파일</th>
                <c:choose>
                	<c:when test="${file.fno ne 0}">
               			 <!-- article.fileDto.ofile -->
		                <td><a href="/Jboard2/fileDownload.do?fno=${file.fno}">${file.ofile}</a>&nbsp;
		                <!-- article.fileDto.download -->
		                <span>${file.download}</span>회 다운로드</td>
               		</c:when>
	                <c:otherwise>
	                	<td>
	                		<span>파일 없음</span>
	                	</td>
	                </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly>${article.content}</textarea>
                </td>
            </tr>                    
        </table>
        
        <div>
            <a href="/Jboard2/delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
            <a href="/Jboard2/modify.do" class="btn btnModify">수정</a>
            <a href="/Jboard2/list.do" class="btn btnList">목록</a>
        </div>

        <!-- 댓글목록 -->
        <section class="commentList">
            <h3>댓글목록</h3> 
			<c:forEach items="${comments}" var="comment">  
            <article>
            	<input type="hidden" name="parent" value="${comment.parent}">
                <span class="nick">${comment.nick}</span>
                <span class="date">${comment.rdate}</span>
                <p class="content">${comment.content}</p>                        
                <div>
                    <a href="#" class="remove" data-no="${comment.no}" data-parent="${comment.parent}">삭제</a>
                    <a href="#" class="modify">수정</a>
                </div>
            </article>
			</c:forEach>
				<c:if test="${comments.size()==0}">
	            	<p class="empty">등록된 댓글이 없습니다.</p>
				</c:if>
        </section>

        <!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="#" id="formComment" method="POST">
            	<input type="hidden" name="parent" value="${article.no}">
            	<input type="hidden" name="writer" value="${sessUser.uid}">
                <textarea name="commentContent" required></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>

    </section>
</main>
<%@ include file="./_footer.jsp"%>