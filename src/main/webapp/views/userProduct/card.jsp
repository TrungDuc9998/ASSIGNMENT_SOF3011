<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	
	<div class="row bg-light">
		<h3 class="text-center mt-3">GIỎ HÀNG CỦA BẠN</h3>
		
		<div class="col-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Sản phẩm</th>
						<th>Hình ảnh</th>
						<th>Đơn giá</th>
						<th>Số lượng</th>
						<th colspan="2">Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userProduct}" var="userPro">
						
							<tr>
								<td>
									${userPro.product.ten}
								</td>
								<td>
									<img alt="" src="/ASSIGNMENT_SOF3011/files/${userPro.product.img}" width=100px height=100px>
								</td>
								<td>
									${userPro.product.donGia}
								</td>
								<td>
									${userPro.soLuong}
								</td>
								<td>
									<!-- Button trigger modal -->
								<button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${userPro.id}">
								  Xoá khỏi giỏ hàng
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModal${userPro.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Xoá sản phẩm khỏi giỏ hàng</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        Bạn có muốn xoá sản phẩm khỏi giỏ hàng không?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
								        <a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/sp/delete?id=${userPro.id}">Xoá</a>
								      </div>
								    </div>
								  </div>
								</div>
								</td>
								
							</tr>	
							
					</c:forEach>
				</tbody>
			</table>
			<div class="row">
				<div class="col-6"></div>
				<div class="col-3">
					<p class="fs-5 text-danger text-center"><i>Tổng tiền: ${tong} VNĐ</i></p>
				</div>
				<div class="col-3">
					<a class="btn btn-danger" type="button" href="/ASSIGNMENT_SOF3011//invoice/oder">Đặt hàng</a>
					
				</div>
				
			</div>
		</div>
		
	</div>
</div>
