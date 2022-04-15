<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="container" style="margin-top:90px">
	<h4 class="text-center">QUẢN LÝ SẢN PHẨM</h4>
	<c:if test="${!empty sessionScope.message }">
	<div class="alert alert-success">
		${sessionScope.message}
		<c:remove var="message" scope="session"/>
	</div>
	</c:if>
	<c:if test="${!empty sessionScope.error }">
		<div class="alert alert-danger">
			${sessionScope.error}
			<c:remove var="error" scope="session"/>
		</div>
	</c:if>
	<div class="row">
		
			<div class="col-4">
				<p class="text-center">Chọn loại sản phẩm để hiển thị sản phẩm</p>
			</div>
			<div class="col-6">
				<form action="/ASSIGNMENT_SOF3011/product/show" method="get">
					<div class="row">
						<div class="col-8">
							<select name="cate_id" class="form-control">
								<c:forEach items="${listCate}" var="cate">
									<option value="${cate.id}">${cate.ten}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-4">
							<button class="btn btn-primary">Hiển thị</button>
						</div>
					</div>	
				</form>
			</div>
			<div class="col-2">
				
			</div>
		
	</div>
	<a type="button" class="btn btn-success" href="/ASSIGNMENT_SOF3011/product/insert">Thêm mới</a>
	<table class="table mt-2" >
	<thead>
		<tr>
			
			<th>Tên sản phẩm</th>
			<th>Hình ảnh</th>
			<th>Số lượng</th>
			<th>Đơn giá</th>
			<th>Màu sắc</th>
			<th>Kích thước</th>
			
			<th colspan="2">thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listPro}" var="pro">
			<tr>
				
				<td>${pro.ten}</td>
				<td>
					<img alt="" src="/ASSIGNMENT_SOF3011/files/${pro.img}" width="100px" height="100px">
				</td>
				<td>${pro.soLuong}</td>
				<td>${pro.donGia}</td>
				<td>${pro.mauSac}</td>
				<td>${pro.kichThuoc}</td>
				
				<td>
					<a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/product/edit?id=${pro.id}">Sửa</a>
				</td>
				<td>
					<!-- Button trigger modal -->
					<button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${pro.id}">
					  Xoá
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal${pro.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Xoá sản phẩm</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn xoá bản ghi này không?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
					        <a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/product/delete?id=${pro.id}">Xoá</a>
					      </div>
					    </div>
					  </div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
