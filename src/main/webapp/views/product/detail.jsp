<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-5">
			<img class="text-center" src="/ASSIGNMENT_SOF3011/files/${product.img}" width="450px" height="450px">
		</div>
		<div class="col-3 mt-3">
		<form action="/ASSIGNMENT_SOF3011/sp/store?id=${product.id}" method="post" >
			
				<h1>${product.ten}</h1>
				<h3 class="text-danger fw-bold">Đơn giá: ${product.donGia} VNĐ</h3>
				<div class="row">
					<div class="col-6">
							<p>Màu sắc:</p>
							<input type="text" value="${product.mauSac }" class="bg-white form-control" disabled>
					</div>
					<div class="col-6">
						<p>Kích thước:</p>
						<input type="text" value="${product.kichThuoc }" class="form-control bg-white" disabled>
					</div>
				</div>
				<label>Số lượng</label>
				<input type="number" min="0" value="1" max="${product.soLuong}" class="form-control" name="soLuong">
					
				
				<button class="btn btn-warning mt-3">Thêm vào giỏ hàng</button>
		</form>
		
		</div>
		<div class="col-2">
		</div>
	</div>
	
</div>
