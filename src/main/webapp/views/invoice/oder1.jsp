<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">ĐẶT HÀNG</h5>
					<p class="ms-3">Hãy nhập thông tin để đặt hàng</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
			</div>
				<div class="card-body">
					<form  action="/ASSIGNMENT_SOF3011//invoice/storee" method="post">
						<div class="form-group">
							<label>Số lượng sản phẩm:</label>
							<input class="form-control" type="number" name="soLuong" min="1" value="1">
						</div>
						<div class="form-group">
							<label>Địa chỉ nhận hàng:</label>
							<input class="form-control" name="diaChi" value="${user}" >
						</div>
						<div class="form-group mt-2 text-center">
							<button class="btn btn-primary ">Đặt hàng</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-3"></div>
	</div>
</div>