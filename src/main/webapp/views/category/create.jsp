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
					<h5 class="ms-3">THÊM MỚI CATEGORY</h5>
					<p class="ms-3">Hãy thêm loại sản phẩm vào hệ thống</p>
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
					<form action="/ASSIGNMENT_SOF3011/category/store" method="post">
						<div class="form-group">
							<label>Tên:</label>
							<input class="form-control" name="ten" >
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