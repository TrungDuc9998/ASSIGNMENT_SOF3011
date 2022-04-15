<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">CẬP NHẬT CATEGORY</h5>
					<p class="ms-3">Hãy cập nhật loại sản phẩm vào hệ thống</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
			</div>
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/category/update?id=${cate.id}" method="post">
						<div class="form-group">
							<label>Tên:</label>
							<input class="form-control" name="ten" value="${cate.ten}" >
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