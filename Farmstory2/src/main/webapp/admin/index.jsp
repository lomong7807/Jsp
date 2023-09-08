<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="./_header.jsp" %>
<main>
	<%@include file ="./_asideAdmin.jsp" %>
    <section>
        <nav>
            <h3>관리자 메인</h3>
        </nav>

        <article>
            <h3>
                <a href="#">상품현황</a>
                <a href="#" class="more">+ 더보기</a>
            </h3>
            <table border="0">
                <tr>
                    <th>상품번호</th>
                    <th>상품명</th>
                    <th>구분</th>
                    <th>가격</th>
                    <th>재고</th>
                    <th>등록일</th>
                </tr>
                <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.pNo}</td>
                    <td>${product.pName}</td>
                    <c:if test="${product.type eq 1}">
                    <td>과일</td>
                    </c:if>
                    <c:if test="${product.type eq 2}">
                    <td>야채</td>
                    </c:if>
                    <c:if test="${product.type eq 3}">
                    <td>곡류</td>
                    </c:if>
                    <td>${product.priceWithComma}원</td>
                    <td>${product.stock}</td>
                    <td>${product.rdate}</td>
                </tr>
                </c:forEach>
            </table>
        </article>

        <article>
            <h3>
                <a href="#">주문현황</a>
                <a href="#" class="more">+ 더보기</a>
            </h3>
            <table border="0">
                <tr>
                    <th>주문번호</th>
                    <th>상품명</th>
                    <th>판매가격</th>
                    <th>수량</th>
                    <th>배송비</th>
                    <th>합계</th>
                    <th>주문자</th>
                    <th>주문일</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderNo}</td>
                    <td>${order.pName}</td>
                    <td>${order.pPriceWithComma}원</td>
                    <td>${order.orderCount}개</td>
                    <c:if test="${order.orderDelivery > 0}">
                    <td>${order.orderDeliveryWithComma}원</td>
                    </c:if>
                    <c:if test="${order.orderDelivery == 0}">
                    <td>무료</td>
                    </c:if>
                    <td>${order.orderTotalWithComma}원</td>
                    <td>${order.nick}</td>
                    <td>${order.orderDate}</td>
                </tr>
                </c:forEach>
            </table>
        </article>
        <article>
            <h3>
                <a href="#">회원현황</a>
                <a href="#" class="more">+ 더보기</a>
            </h3>
            <table border="0">
                <tr>
                    <th>회원아이디</th>
                    <th>이름</th>
                    <th>닉네임</th>
                    <th>휴대폰</th>
                    <th>이메일</th>
                    <th>등급</th>
                    <th>회원가입일</th>
                </tr>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.uid}</td>
                    <td>${user.name}</td>
                    <td>${user.nick}</td>
                    <td>${user.hp}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.regDate}</td>
                </tr>
                </c:forEach>
            </table>
        </article>
    </section>
</main>
<%@include file ="./_footer.jsp" %>