<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">CẬP NHẬT SẢN PHẨM</h5>
					<p class="ms-3">Hãy cập nhật sản phẩm vào hệ thống</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
			</div>
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/product/update?id=${pro.id}" method="post"  enctype="multipart/form-data">
						
						<div class="form-group">
							<label>Tên:</label>
							<input class="form-control" name="ten" value="${pro.ten}">
						</div>
						<div class="form-group mt-2">
							<label>Hình ảnh</label>
							<input type="file" class="form-control" name="img">
							
						</div>
						<div class="form-group">
							<label>Số Lượng:</label>
							<input class="form-control" name="soLuong" value="${pro.soLuong}" >
						</div>
						<div class="form-group">
							<label>Đơn giá:</label>
							<input class="form-control" name="donGia" value="${pro.donGia}">
						</div>
						<div class="form-group">
							<label>Màu sắc:</label>
							<input class="form-control" name="mauSac" value="${pro.mauSac}" >
						</div>
						<div class="form-group">
							<label>Kích thước:</label>
							<input class="form-control" name="kichThuoc" value="${pro.kichThuoc}">
						</div>
						
						<div class="form-group mt-2 text-center">
							<button class="btn btn-primary ">Cập nhật</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-3"></div>
	</div>
</div>