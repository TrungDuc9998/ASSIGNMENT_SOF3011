<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">THÊM MỚI SẢN PHẨM</h5>
					<p class="ms-3">Hãy thêm sản phẩm vào hệ thống</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
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
			</div>
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/product/store" method="post" enctype="multipart/form-data">
						
						<label>Loại sản phẩm:</label>
						<select name="cate_id" class="form-control">
							<c:forEach items="${listCate}" var="cate">
								<option value="${cate.id}">${cate.ten}</option>
							</c:forEach>
						</select>
						<div class="form-group">
							<label>Tên:</label>
							<input class="form-control" name="ten" >
							<c:if test="${!empty sessionScope.trong }">
								<div class="alert alert-danger">
									${sessionScope.trong}
									<c:remove var="trong" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Hình ảnh;</label>
							<input type="file" class="form-control" name="img">
							<c:if test="${!empty sessionScope.img }">
								<div class="alert alert-danger">
									${sessionScope.img}
									<c:remove var="img" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Số Lượng:</label>
							<input class="form-control" name="soLuong" >
							<c:if test="${!empty sessionScope.trong }">
								<div class="alert alert-danger">
									${sessionScope.trong}
									<c:remove var="trong" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Đơn giá:</label>
							<input class="form-control" name="donGia" >
							<c:if test="${!empty sessionScope.trong }">
								<div class="alert alert-danger">
									${sessionScope.trong}
									<c:remove var="trong" scope="session"/>
								</div>
							</c:if>
							<c:if test="${!empty sessionScope.donGia }">
								<div class="alert alert-danger">
									${sessionScope.donGia}
									<c:remove var="donGia" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Màu sắc:</label>
							<input class="form-control" name="mauSac" >
							<c:if test="${!empty sessionScope.trong }">
								<div class="alert alert-danger">
									${sessionScope.trong}
									<c:remove var="trong" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Kích thước:</label>
							<input class="form-control" name="kichThuoc" >
							<c:if test="${!empty sessionScope.trong }">
								<div class="alert alert-danger">
									${sessionScope.trong}
									<c:remove var="trong" scope="session"/>
								</div>
								<c:if test="${!empty sessionScope.donGia }">
								<div class="alert alert-danger">
									${sessionScope.donGia}
									<c:remove var="donGia" scope="session"/>
								</div>
							</c:if>
							</c:if>
						</div>
						
						<div class="form-group mt-2 text-center">
							<button class="btn btn-primary ">Thêm mới</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-3"></div>
	</div>
</div>