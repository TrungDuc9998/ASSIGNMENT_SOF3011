<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	
	<div class="row bg-light">
		<h3 class="text-center">GIỎ HÀNG CỦA BẠN</h3>
		<div class="col-12">
			<table class="table bg-primary">
				<thead>
					<tr>
					<th>Sản phẩm</th>
					<th>Đơn giá</th>
					<th>Số lượng</th>
					<th>Giá tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userProduct}" var="userPro">
						<tr>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
	</div>
</div>
