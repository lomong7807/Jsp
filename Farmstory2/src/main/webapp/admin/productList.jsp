<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file ="./_header.jsp" %>

<main>
    <%@include file ="./_asideAdmin.jsp" %>
    <section id="productList">
        <nav>
            <h3>상품목록</h3>
            <p class="sort">
                <a href="${ctxPath}/admin/productList.do?type=0" class="${type eq 0 ? 'on':''}">전체 
                <c:if test="${type eq 0}">
                <span>(${total})</span>
                </c:if>
                 |</a>
                <a href="${ctxPath}/admin/productList.do?type=1" class="${type eq 1 ? 'on':''}">과일 
                <c:if test="${type eq 1}">
                <span>(${total})</span>
                </c:if>
                 |</a>
                <a href="${ctxPath}/admin/productList.do?type=2" class="${type eq 2 ? 'on':''}">야채 
                <c:if test="${type eq 2}">
                <span>(${total})</span>
                </c:if>
                 |</a>
                <a href="${ctxPath}/admin/productList.do?type=3" class="${type eq 3 ? 'on':''}">곡류 
                <c:if test="${type eq 3}">
                <span>(${total})</span>
                </c:if>
                </a>
            </p>
        </nav>

        <article>

            <table border="0">
                <tr>
                    <th><input type="checkbox" name="all"/></th>
                    <th>사진</th>
                    <th>상품번호</th>
                    <th>상품명</th>
                    <th>구분</th>
                    <th>가격</th>
                    <th>재고</th>
                    <th>등록일</th>
                </tr>
                <c:forEach var="product" items="${products}">
                <tr>
                    <td><input type="checkbox" name=""/></td>
                    <td><img src="/Farmstory2/thumb/${product.thumb1}" class="thumb" alt="샘플1"></td>
                    <td>${product.pNo}</td>
                    <td>${product.pName}</td>
                    <td>${product.type}</td>
                    <td>${product.priceWithComma}원</td>
                    <td>${product.stock}</td>
                    <td>${product.rdate}</td>
                </tr>
                </c:forEach>
            </table>

            <p>
                <a href="#" class="productDelete">선택삭제</a>
                <a href="./productRegister.do" class="productRegister">상품등록</a>
            </p>
            
            <p class="paging">
                <c:if test="${pageGroupStart > 1}">
		            	<a href="${ctxPath}/admin/productList.do?type=${type}&pg=${pageGroupStart - 1}"><</a>
	            </c:if>
	            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	            	<a href="${ctxPath}/admin/productList.do?type=${type}&pg=${i}&" class="num ${currentPage == i?'on':'off'}">[${i}]</a>
	            </c:forEach>
	            <c:if test="${pageGroupEnd < lastPageNum}">
	            	<a href="${ctxPath}/admin/productList.do?type=${type}&pg=${pageGroupEnd + 1}">></a>
	            </c:if>
            </p>

        </article>

        
    </section>
</main>
<%@include file ="./_footer.jsp" %>