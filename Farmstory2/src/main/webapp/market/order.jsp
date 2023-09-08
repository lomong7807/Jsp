<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<script>
	$(function(){
		$('#btnBuy').click(function(e){
			e.preventDefault();
			$('#formOrder').submit();
		});// click buy end
		
		$('#btnShopping').click(function(e){
			e.preventDefault();
			if(confirm('주문을 취소하시겠습니까?')){
				location.href='/Farmstory2/market/list.do?type=0';
			}
		}); // shopping end
	});

</script>

<%@include file ="./_asideMarket.jsp" %>
        <article class="order">
            <nav>
                <img src="${ctxPath}/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>

            <!-- 내용 시작 -->
            <h3>주문상품 확인</h3>
            <div class="info">
                <img src="${ctxPath}/thumb/${dto.thumb2}" alt="상품 사진">

                <table border="0">                            
                    <tr>
                        <td>상품명</td>
                        <td>${dto.pName}</td>
                    </tr>
                    <tr>
                        <td>상품코드</td>
                        <td>${dto.pNo}</td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <c:choose>
                        	<c:when test="${dto.total < 30000}">
                        		<td class="delivery">${dto.delivery}원</td>
                        	</c:when>
                        	<c:otherwise>
                        		<td class="delivery">무료</td>
                        	</c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>판매가격</td>
                        <td>${dto.priceWithComma}원</td>
                    </tr>
                    <tr>
                        <td>구매수량</td>
                        <td class="count">${dto.count}</td>
                    </tr>
                    <tr>
                        <td>합계</td>
                        <c:choose>
                        	<c:when test="${dto.total > 30000}">
                        		<td class="total">${dto.totalWithComma}원</td>
                        	</c:when>
                        	<c:otherwise>
                        		<td class="total">${dto.totalPriceWithComma}원</td>
                        	</c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
            <h3>주문정보 입력</h3>
            <div class="shipping">
            	<form id="formOrder" action="${ctxPath}/market/orderComplete.do?type=0" method="post">
            	<input type="hidden" name="orderProduct" 	value="${dto.pNo}">
            	<input type="hidden" name="orderCount" 	value="${dto.count}">
            	<input type="hidden" name="orderDelivery" value="${dto.delivery}">
            	<input type="hidden" name="orderPrice"	value="${dto.price}">
            	<c:choose>
                	<c:when test="${dto.total > 30000}">
                		<input type="hidden" name="orderTotal" 	value="${dto.total}">
                	</c:when>
                	<c:otherwise>
                		<input type="hidden" name="orderTotal" 	value="${dto.totalPrice}">
                	</c:otherwise>
                </c:choose>
            	<input type="hidden" name="orderTotal" 	value="${dto.total}">
            	<input type="hidden" name="orderUser" 	value="${sessUser.uid}">
                <table>
                    <tr>
                        <td>받는분</td>
                        <td><input type="text" name="receiver" required value="${sessUser.name}"></td>
                    </tr>
                    <tr>
                        <td>휴대폰</td>
                        <td><input type="text" name="hp" required value="${sessUser.hp}" minlength="13" maxlength="13"></td>
                    </tr>
                    <tr>
                        <td>배송주소</td>
                        <td>
                            <input type="text" name="zip" readonly required value="${sessUser.zip}"><button id="btnZip">우편번호 검색</button>
                            <input type="text" name="addr1" required placeholder="기본주소 검색" value="${sessUser.addr1}">
                            <input type="text" name="addr2" placeholder="상세주소 입력" value="${sessUser.addr2}">
                        </td>
                    </tr>
                    <tr>
                        <td>기타</td>
                        <td>
                            <textarea name="etc"></textarea>
                        </td>
                    </tr>
                </table>
                </form>
            </div>

            <p>
                <a href="#" id="btnBuy"><img src="${ctxPath}/images/market_btn_buy.gif" alt="구매하기"></a>
                <a href="#" id="btnShopping"><img src="${ctxPath}/images/market_btn_shopping.gif"></a>
            </p>
            <!-- 내용 끝 -->
            
        </article>
    </section>

</div>
<%@include file ="../_footer.jsp" %>