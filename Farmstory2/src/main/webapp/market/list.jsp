<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<%@include file ="./_asideMarket.jsp" %>
        <article class="list">
            <nav>
                <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>

            <!-- 내용 시작 -->
            <p class="sort">
                <a href="${ctxPath}/market/list.do?type=0" class="${type eq 0 ? 'on':''}">전체 
	                <c:if test="${type eq 0}">
	                	<span>(${total})</span>
	                </c:if>
                 |</a>
                <a href="${ctxPath}/market/list.do?type=1" class="${type eq 1 ? 'on':''}">과일 
	                <c:if test="${type eq 1}">
	                	<span>(${total})</span>
	                </c:if>
                 |</a>
                <a href="${ctxPath}/market/list.do?type=2" class="${type eq 2 ? 'on':''}">야채 
	                <c:if test="${type eq 2}">
	                	<span>(${total})</span>
	                </c:if>
                 |</a>
                <a href="${ctxPath}/market/list.do?type=3" class="${type eq 3 ? 'on':''}">곡류 
	                <c:if test="${type eq 3}">
	                	<span>(${total})</span>
	                </c:if>
                </a>
            </p>
            <table border="0">
                <c:forEach var="product" items="${products}">
                <tr>
                    <td>
                        <a href="./view.do?pNo=${product.pNo}"><img src="${ctxPath}/thumb/${product.thumb1}" alt="${product.pName}" class="thumb"></a>
                    </td>
                    <td>
                    	<c:if test="${product.type eq 1}">
                    	과일
                    	</c:if>
                    	<c:if test="${product.type eq 2}">
                    	야채
                    	</c:if>
                    	<c:if test="${product.type eq 3}">
                    	곡류
                    	</c:if>
                    </td>
                    <td><a href="${ctxPath}/market/view.do?pNo=${product.pNo}">${product.pName}</a></td>
                    <td><strong>${product.priceWithComma}</strong>원</td>
                </tr>
                </c:forEach>
            </table>

            <p class="paging">
                <c:if test="${pageGroupStart > 1}">
		            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${pageGroupStart - 1}"><</a>
	            </c:if>
	            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${i}&" class="num ${currentPage == i?'on':'off'}">[${i}]</a>
	            </c:forEach>
	            <c:if test="${pageGroupEnd < lastPageNum}">
	            	<a href="${ctxPath}/market/list.do?type=${type}&pg=${pageGroupEnd + 1}">></a>
	            </c:if>
            </p>

            <!-- 내용 끝 -->

        </article>
    </section>

</div>
<%@include file ="../_footer.jsp" %>