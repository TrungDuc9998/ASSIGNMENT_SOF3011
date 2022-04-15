<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div class="container" style="margin-top:90px">
	<h4 class="text-center">QUẢN TRỊ ĐƠN HÀNG</h4>
	<a type="button" class="btn btn-warning" href="/ASSIGNMENT_SOF3011/user/insert">Thêm mới</a>
	<table class="table mt-2" >
	<thead>
		<tr>
			<th>Người đặt hàng</th>
			<th>Sản phẩm</th>
			<th>Hình ảnh</th>
			<th>Đơn giá</th>
			<th>Số lượng</th>
			<th>Thành tiền</th>
			<th>Địa chỉ</th>
			<th>Thời gian</th>
			<th colspan="2">thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listBill}" var="bill">
			<tr>
				<td>${bill.user.hoTen}</td>
				<td>${bill.product.ten}</td>
				<td>
					<img alt="" src="/ASSIGNMENT_SOF3011/files/${bill.product.img}" width=100px height=100px>
				</td>
				<td>${bill.product.donGia}</td>
				<td>${bill.soLuong}</td>
				<td>${bill.thanhTien}</td>
				<td>${bill.diaChi}</td>
				<td>${bill.thoiGian}</td>
				
				<c:choose>
						<c:when test="${bill.trangThai==1}"> 
						  <td>
							<i class="text-success fw-bold">Đã xác nhận</i>	
							</td>	
						</c:when>
						<c:when test="${bill.trangThai==0}">
							<td>	
								<button type="button"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${bill.id}">
								 Xác nhận đơn hàng
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModal${bill.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title text-warning" id="exampleModalLabel">FPT POLYTECHNIC</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        Bạn có muốn xác nhận đơn hàng này không?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
								       <form action="/ASSIGNMENT_SOF3011/invoice/update?id=${bill.id}" method="post">
											<button class="btn btn-danger">Xác nhận</button>
										</form>
								      </div>
								    </div>
								  </div>
								</div>
							</td>
						</c:when>
						
				</c:choose>
				<c:choose>
						<c:when test="${bill.trangThai==2}"> 
						  <td>
							<i class="text-danger">Đã huỷ hàng</i>	
						</td>	
						</c:when>
						<c:when test="${bill.trangThai==0}">
							<td>	
								<button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal1${bill.id}">
								 Huỷ đơn hàng
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModal1${bill.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Huỷ đơn hàng</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        Bạn có muốn huỷ đơn hàng này không?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
								       <form action="/ASSIGNMENT_SOF3011/invoice/cancel?id=${bill.id}" method="post">
								       		
											<button class="btn btn-danger">Huỷ đơn hàng</button>
										</form>
								      </div>
								    </div>
								  </div>
								</div>
							</td>
						</c:when>		
				</c:choose>
				
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
