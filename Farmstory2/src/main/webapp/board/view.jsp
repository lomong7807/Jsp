<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
    <section class="view">
        <h3>글보기</h3>
        <input type="text" name="writer" readonly value="작성자: 닉네임">
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="title" value="제목" readonly/></td>
            </tr>
            <tr>
                <td>첨부파일</td>
                <td>
                    <a href="#">2020년 상반기 매출자료.xls</a>
                    <span>7회 다운로드</span>
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea name="content" readonly>내용</textarea>
                </td>
            </tr>
        </table>
        <div>
            <a href="./delete.jsp" class="btnDelete">삭제</a>
            <a href="./modify.jsp" class="btnModify">수정</a>
            <a href="/Farmstory2/board/list.jsp" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <article class="comment">
            	<form action="/Farmstory2/board/proc/commentUpdate.jsp" method="post">
	            	<input type="hidden" name="no" value="">
	            	<input type="hidden" name="parent" value="">
	                <span>
	                    <span>닉네임</span>
	                    <span>날짜</span>
	                </span>
	                <textarea name="comment" readonly>내용</textarea>
	                
	                <!-- 현재 이용자가 댓글 작성자와 동일한지 확인하여 삭제와 수정을 보이게끔 한다. -->
	                <div>
	                	<!-- for문 안에서 반복되기 때문에 id가 반복되면 안돼서 class로 사용 -->
	                    <a href="/Farmstory2/board/proc/commentDelete.jsp" class="del">삭제</a>
	                    
	                    <a href="/Farmstory2/board/view.jsp" class="can">취소</a>
	                    <a href="#" class="mod">수정</a> <!-- AJAX 처리해야됨 -->
	                </div>
                </form>
            </article>
           	<!-- 등록된 댓글이 없다면 아래를 출력 -->
           	<p class="empty">등록된 댓글이 없습니다.</p>
        </section>

        <!-- 댓글입력폼 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="./proc/commentInsert.jsp" method="post">
            	<input type="hidden" name="group" value=""/>
            	<input type="hidden" name="cate" value=""/>
            	<input type="hidden" name="parent" value=""/>
            	<input type="hidden" name="writer" value=""/>
                <textarea name="content"></textarea>
                <div>
                    <a href="#" class="btnCancel">취소</a>
                    <input type="submit" class="btnWrite" value="작성완료"/>
                </div>
            </form>
        </section>
    </section>
    	   		<!-- 내용 끝 -->
   		</article>
	</section>
</div>
<%@include file ="../_footer.jsp" %>