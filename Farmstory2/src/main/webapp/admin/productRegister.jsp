<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="./_header.jsp" %>
<script>
	$(function(){
		
		$('.submitBtn').click(function(){
			alert('클릭');
			if($('select[name=type]'.val() === "구분"){
				alert("구분을 선택하세요.");
				return false;
			}
			if(!$('input[name=delivery]:radio').is(':checked')){
			    alert("배송비를 선택하세요.");
			    return false;
			}
		});
	});
</script>
<main>
    <%@include file ="./_asideAdmin.jsp" %>
    <section id="productRegister">
        <nav>
            <h3>상품등록</h3>
        </nav>

        <article>
            <form action="#" method="post" enctype="multipart/form-data">
            <input type="hidden" name="seller" value="${sessUser.uid}"/>
                <table border="0">
                    <tr>
                        <td>상품명</td>
                        <td><input type="text" name="productName"/></td>
                    </tr>
                    <tr>
                        <td>구분</td>
                        <td>
                            <select name="type">
                                 <option selected disabled>구분</option>
                                <option value="1">과일</option>
                                <option value="2">야채</option>
                                <option value="3">곡류</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>가격</td>
                        <td><input type="text" pattern="[0-9]+" required name="price"/></td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td>
                            <label><input type="radio" name="delivery" value="2000">2,000원</label>                                    
                            <label><input type="radio" name="delivery" value="3000">3,000원</label>
                            <label><input type="radio" name="delivery" value="5000">5,000원</label>
                            <label><input type="radio" name="delivery" value="0">무료</label>
                        </td>
                    </tr>
                    <tr>
                        <td>재고</td>
                        <td><input type="text" pattern="[0-9]+" required name="stock"/></td>
                    </tr>
                    <tr>
                        <td>상품이미지</td>
                        <td>
                            <p>
                                <span>상품목록 이미지(약 120 x 120)</span>
                                <input type="file" name="thumb1"/>
                            </p>
                            <p>
                                <span>기본정보 이미지(약 240 x 240)</span>
                                <input type="file" name="thumb2"/>
                            </p>
                            <p>
                                <span>상품설명 이미지(약 750 x Auto)</span>
                                <input type="file" name="thumb3"/>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>기타</td>
                        <td>
                            <textarea name="etc"></textarea>
                        </td>
                    </tr>
                </table>

                <p>
                    <a href="./productList.do" class="btnCancel">취소</a>
                    <input class="submitBtn" type="submit" value="상품등록"/>
                </p>
            </form>
        </article>                
    </section>
</main>
<%@include file ="./_footer.jsp" %>