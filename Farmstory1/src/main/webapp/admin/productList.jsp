<%@page import="kr.farmstory1.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	String type = request.getParameter("type");
	if(type == null){
		type = "0";
	}
	
	ProductDAO dao = new ProductDAO();
	
	// 페이지 관련 변수  선언
	int start = 0;
	int currentPage = 1;		
	int total = dao.selectCountProductsTotal(type);				// 전체 게시물 갯수
	int lastPageNum = 0;		// 마지막 페이지 번호
	int pageGroupCurrent = 1;	
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산
	// /Farmstory1/board/list.jsp?pg=? 의 값을 가져와서 pg에 넣기
	if(pg != null){
		currentPage = Integer.parseInt(pg);
	}
	
	// Limit 시작값 계산
	start = (currentPage - 1) * 10; 
	
	// 전체 상품 갯수 조회
	total = dao.selectCountProductsTotal(type);
	
	// 마지막 페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	// 페이지  그룹 계산
	pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작번호 계산
	pageStartNum = total - start;
	
	List<ProductDTO> products = dao.selectProducts(type, start);
%>
<main>
    <%@ include file="./_aside.jsp" %>
    <section id="productList">
        <nav>
            <h3>상품목록</h3>
            <p class="sort">
                <a href="/Farmstory1/admin/productList.jsp?type=0" class="<%=type.equals("0") ? "on":""%>">전체<%= type.equals("0")?"("+total+")":""%> |</a>
                <a href="/Farmstory1/admin/productList.jsp?type=1" class="<%=type.equals("1") ? "on":""%>">과일<%= type.equals("1")?"("+total+")":""%> |</a>
                <a href="/Farmstory1/admin/productList.jsp?type=2" class="<%=type.equals("2") ? "on":""%>">야채<%= type.equals("2")?"("+total+")":""%> |</a>
                <a href="/Farmstory1/admin/productList.jsp?type=3" class="<%=type.equals("3") ? "on":""%>">곡류<%= type.equals("3")?"("+total+")":""%></a>
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
                <% for(ProductDTO product : products){ %>
                <tr>
                    <td><input type="checkbox" name=""/></td>
                    <td><img src="/Farmstory1/thumb/<%= product.getThumb1() %>" class="thumb" alt="샘플1"></td>
                    <td><%= product.getpNo() %></td>
                    <td><%= product.getpName() %></td>
                    <td><%= product.getType() %></td>
                    <td><%= product.getPriceWithComma()%></td>
                    <td><%= product.getStock() %></td>
                    <td><%= product.getRdate() %></td>
                </tr>
                <% } %>
            </table>

            <p>
                <a href="#" class="productDelete">선택삭제</a>
                <a href="./productRegister.jsp" class="productRegister">상품등록</a>
            </p>
            
            <p class="paging">
            	<% if(lastPageNum > 10){ %>
		    	<a href="/Farmstory1/admin/productList.jsp?type=<%=type %>&pg=<%= 1 %>" class="prev">처음</a>
		    	<% } %>
            	<% if(pageGroupStart > 1){ %>
                <a href="/Farmstory1/admin/productList.jsp?type=<%=type %>&pg=<%=pageGroupStart -1 %>" class="prev"><</a>
       			<% } %>
       			<% for(int i=pageGroupStart; i <= pageGroupEnd; i++){ %>
                <a href="/Farmstory1/admin/productList.jsp?type=<%=type %>&pg=<%=i %>" class="num <%= (currentPage == i)?"current":""%>"><%=i %></a>
                <% } %>   
                <% if(pageGroupEnd < lastPageNum){ %>
                <a href="/Farmstory1/admin/productList.jsp?type=<%=type %>&pg=<%=pageGroupEnd + 1 %>" class="next">></a>
                <% } %>
                <% if(lastPageNum > 10){ %>
		    	<a href="/Farmstory1/admin/productList.jsp?type=<%=type %>&pg=<%= lastPageNum %>" class="next">마지막</a>
		    	<% } %>
            </p>

        </article>

        
    </section>
</main>
<%@ include file="./_footer.jsp" %>