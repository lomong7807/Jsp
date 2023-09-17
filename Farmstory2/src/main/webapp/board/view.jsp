<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
<script>
	$(function(){
		// 댓글 수정 초기값. 댓글 수정을 누르기 전에는 수정 중인 댓글이 없기때문에 null로 설정
		let editingComment = null;
		/////////////////////////////////////////////////////////////////////
		////////////////////////    댓글 삭제   ///////////////////////////////
		/////////////////////////////////////////////////////////////////////
		// 댓글 삭제(동적 생성 이벤트 구현)
		// 페이지에서 작성된 댓글에 대한 삭제 이벤트는 구현되지 않기때문에 동적 생성 이벤트로 구현해야 한다.
		$(document).on('click', '.remove', function(e){
			e.preventDefault();
			//alert('삭제');
			
			const no = $(this).data('no');
			const parent = $(this).data('parent');
			const article = $(this).parent().parent();
			
			console.log('no : '+ no);
			console.log('parent : '+ parent);
			
			const jsonData ={
					"kind": "REMOVE",
					"no" : no,
					"parent" : parent,
			}
			
			if(confirm('정말 삭제하시겠습니까?') == true){
				$.ajax({
					url: '/Farmstory2/comment.do',
					type: 'post',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						console.log(data.result);
						console.log(data.parentComment);
						const parentComment = data.parentComment;
						// 삭제에 성공하면
						if(data.result > 0){
							
							if(editingComment != null){
								const prevContent = $(editingComment).text();
								cancelEdit(editingComment);
								editingComment.val(prevContent);								
							}
							
							//alert('댓글이 삭제되었습니다.');
							// 화면처리
							article.remove();
							
							const empty = $('.empty');
							if(parentComment == 0){
								const article = `<p class="empty">등록된 댓글이 없습니다.</p>`;
								$('.commentList').append(article);
							}
						}
						editingComment = null;
						return editingComment;
					}
				}); //ajax end
			} // confirm
		})//removeComment end
		
		/////////////////////////////////////////////////////////////////////
		////////////////////////    댓글 입력   ///////////////////////////////
		/////////////////////////////////////////////////////////////////////
		$('#btnComment').click(function(e){
			e.preventDefault();
			
			if($('#formComment > textarea[name=commentContent]').val().length === 0){
				alert('내용을 입력하세요.');
				return;
			}else if(editingComment !== null){
				if(confirm('댓글 수정을 취소하시겠습니까?')){
					let prevContent = editingComment.text();
					cancelEdit(editingComment);
					editingComment.val(prevContent);
					editingComment = null;
					return editingComment;
				}
			}
			//alert('click');
			
			const parent = $('#formComment > input[name=parent]').val(); // refboardno
			const content = $('#formComment > textarea[name=commentContent]').val(); // replycontents
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
						const maxNo = data.maxNo;
						const parent = data.parent;
						const parentComment = data.parentComment;
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2, 4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						let now = null;
						if(month < 10 & date < 10){
							now = year + "-0"+month+"-0"+date;	
						}else if(month < 10 & date >= 10){
							now = year + "-0"+month+"-"+date;
						}else if(month >=10 & date < 10){
							now = year + "-"+month+"-0"+date;
						}else{
							now = year + "-"+month+"-"+date;
						}
						
						
						
						const article = `<article>
											<input type="hidden" name="no" value=`+maxNo+`>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>`+now+`</span>
											<textarea class='content' readonly>`+content+`</textarea>
											<div>
												<a href='#' class='remove' data-no=`+maxNo+` data-parent=`+parent+` data-parentComment=`+parentComment+`>삭제</a>
												<a href='#' class='cancle' data-no=`+maxNo+` data-parent=`+parent+`>취소</a>
												<a href='#' class='modify' data-no=`+maxNo+` data-parent=`+parent+`>수정</a>
											</div>
										</article>`;
										
						$('.commentList').append(article);
						$('.cancle').hide();
						// 댓글 입력 완료시 댓글 입력창 비움
						$('#formComment > textarea[name=commentContent]').val('');
						// 입력된 댓글이 없으면 등록된 댓글이 없다는 댓글창 띄움
						const empty = $('.empty');
						
						if(empty.is(':visible')){
							empty.remove();
						}
						editingComment = null;
						return editingComment;
					}else{
						alert('댓글 등록이 실패했습니다.');
					}
				}
			}); //ajax end
		})// 댓글 입력 end

		
		/////////////////////////////////////////////////////////////////////
		////////////////////////    댓글 입력 취소   ///////////////////////////////
		/////////////////////////////////////////////////////////////////////
		$('#btnCancel').click(function(e){
			e.preventDefault();
			if(confirm('입력된 내용이 삭제됩니다. 취소하시겠습니까?')){
				$('form > textarea[name=commentContent]').val('');
			}
		});// btnCancle end
		
		/////////////////////////////////////////////////////////////////////
		////////////////////////    댓글 수정   ///////////////////////////////
		/////////////////////////////////////////////////////////////////////
		// 수정중인 댓글의 수정 취소 함수
		function cancelEdit(comment){
			// 수정중인 댓글의 클래스 삭제
			comment.removeClass('modi');
			// readonly로 변경
			comment.attr('readonly',true);
			// 수정완료를 수정으로 변경
			comment.next().children('.modify').text('수정');
			// 취소버튼 숨김
			comment.next().children('.cancle').hide();
		}
		// 댓글 수정버튼을 누른다.
		$(document).on('click', '.modify', function(e){
			e.preventDefault();
			
			// txt = this = 수정버튼 
			const txt = $(this).text();
			
			const prevContent = $(editingComment).text();
			console.log(prevContent);
			if(editingComment !== null & txt == '수정'){
				if(confirm('댓글수정을 취소하시겠습니까?')){
					cancelEdit(editingComment);
					editingComment.val(prevContent);
				}
			}
			// textarea contentComment
			editingComment = $(this).parent().prev();
			
			
			
			// txt의 이름이 '수정'이라고 돼있으면
			if(txt == '수정'){
				
				// 수정버튼의 부모의 이전 태그(comment textarea)에 'modi' 클래스를 추가한다. 
				$(this).parent().prev().addClass('modi');
				//readonly 해제
				$(this).parent().prev().attr('readonly', false);
				// 수정버튼의 부모의 이전 태그인 textarea에 포커스가 맞춰진다.
				$(this).parent().prev().focus();
				// 수정버튼의 이름이 '수정완료'로 바뀐다.
				$(this).text('수정완료');
				// 수정버튼의 이전 태그(취소)가 보인다.
				$(this).prev().show();
			
				console.log('수정');
			// 수정버튼의 이름이 현재 '수정완료'라고 돼있으면
			}else if(txt == '수정완료'){
				
				// '수정완료' 클릭했을때 confirm을 받는다.
				if(confirm('정말 수정 하시겠습니까?')){
					
					const content = $(this).parent().prev().val();
					const no = $(this).parent().parent().children().first().val();
					
					const prevContent = $(editingComment).text();
					
					if(content !== prevContent){
						console.log("modiContent : "+content);
						
						const jsonData = {
								"no" : no,
								"content" : content
						};
						
						$.ajax({
							url: '/Farmstory2/comment.do',
							type: 'get',
							data: jsonData,
							dataType: 'json',
							success: function(data){
								console.log(data.result);
								// 댓글 수정에 성공하면
								if(data.result > 0){
									//alert('댓글이 수정되었습니다.');
								}
							}
						}); //ajax end
					}
					
					// 수정모드 해제
					$(this).parent().prev().removeClass('modi');
					$(this).parent().prev().attr('readonly', true);
					$(this).text('수정');
					$(this).prev().hide();
					editingComment = null;
					return editingComment;
					console.log('수정완료');
				} // confirm end
			} // else if end
		}); // modify click end
		
		/////////////////////////////////////////////////////////////////////
		////////////////////////    수정 취소  ////////////////////////////////
		/////////////////////////////////////////////////////////////////////
		$(document).on('click', '.cancle', function(e){
			e.preventDefault();
			if(confirm('댓글수정을 취소하시겠습니까?')){

				const prevContent = $(this).parent().prev().text();
				console.log(prevContent);
				
				$(this).parent().prev().val(prevContent);
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).next().text('수정');
				$(this).hide();
				editingComment = null;
				return editingComment;
			}
		}); // cancle end
	})// end
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
                	<c:choose>
                		<c:when test="${article.file > 0 }">
                    	<a href="${ctxPath}/fileDownload.do?fno=${file.fno}">${file.ofile}</a>
                    	<span> | 다운로드 수 [${file.download}]</span>
                    	</c:when>
                    	<c:otherwise>
                    	<span>-</span>
                    	</c:otherwise>
                    </c:choose>
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
        	<c:if test="${sessUser.uid eq article.writer}">
            <a href="${ctxPath}/delete.do?group=${group}&cate=${cate}&no=${article.no}" class="btnDelete">삭제</a>
            <a href="${ctxPath}/board/modify.do?group=${group}&cate=${cate}&no=${article.no}" class="btnModify">수정</a>
            </c:if>
            <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <c:forEach items="${comments}" var="comment">  
            <article class="comment">
	            	<input type="hidden" name="no" value="${comment.no}">
	            	<input type="hidden" name="parent" value="${comment.parent}">
                    <span class="nick">${comment.nick}</span>
                    <span class="date">${comment.rdate}</span>
	                <textarea class="content" name="content" readonly >${comment.content}</textarea>
	                <!-- 현재 이용자가 댓글 작성자와 동일한지 확인하여 삭제와 수정을 보이게끔 한다. -->
	                <c:if test="${sessUser.uid eq  comment.writer}">
	                <div>
	                	<!-- for문 안에서 반복되기 때문에 id가 반복되면 안돼서 class로 사용 -->
	                    <a href="#" data-no="${comment.no}" data-parent="${comment.parent}" data-parentComment="${article.comment}" class="remove">삭제</a>
	                    <a href="#" class="cancle">취소</a>
	                    <a href="#" data-no="${comment.no}" class="modify">수정</a> <!-- AJAX 처리해야됨 -->
	                </div>
	                </c:if>
            </article>
            </c:forEach>
            
           	<!-- 등록된 댓글이 없다면 아래를 출력 -->
           	<c:if test="${article.comment==0}">
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
                    <a href="#" id="btnCancel" class="btnCancel">취소</a>
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