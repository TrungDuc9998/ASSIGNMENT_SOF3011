<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">ĐỊA CHỈ MỚI</h5>
					<p class="ms-3">Để đặt hàng vui lòng thêm địa chỉ nhận hàng</p>
				</div>
				
			</div>
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/invoice/store" method="post">
						
						<div class="form-group">
							<input class="form-control"  value="${idUserPro}"  name="idUserPro" >
						</div>
						<div class="form-group">
							<input class="form-control"  name="hoTen" placeholder="Họ và tên">
						</div>
						<div class="form-group mt-2">
							<input class="form-control" type="text" name="sdt" placeholder="Số điện thoại">
						</div>
						<div class="form-group mt-2">
							<input class="form-control" type="text" name="diaChi" placeholder="Địa chỉ cụ thể">
						</div>
						<div class="form-group mt-2 text-center">
							<button class="btn btn-outline-warning border border-light ">Trở lại</button>
							<button class="btn btn-danger ">Hoàn thành</button>
							
						</div>
						
					</form>
				</div>
			</div>
		</div>
		<div class="col-4"></div>
	</div>
</div>