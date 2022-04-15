<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">ĐĂNG NHẬP</h5>
					<p class="ms-3">Chào bạn quay lại</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
			</div>
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/login" method="post">
						<div class="form-group">
							<input class="form-control" name="email" placeholder="Nhập email của bạn">
						</div>
						<div class="form-group mt-2">
							<input class="form-control" type="password" name="password" placeholder="Nhập mật khẩu của bạn">
						</div>
						<div class="form-group mt-2 text-center">
							<button class="btn btn-primary ">Đăng nhập</button>
						</div>
						<div class="form-group mt-2 text-center">
							<a>Bạn quên mật khẩu?</a>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-4"></div>
	</div>
</div>