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
					<h5 class="ms-3">ĐĂNG KÝ</h5>
					<p class="ms-3">Hãy đăng ký để sử dụng hệ thống</p>
				</div>
				<div class="col-6 text-center">
					<img class="ms-5" src="/ASSIGNMENT_SOF3011/image/logo.svg">
				</div>
			</div>
				
				<div class="card-body">
					<form action="/ASSIGNMENT_SOF3011/user/registerStore" method="post" enctype="multipart/form-data">
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
						
						<div class="form-group">
							Họ tên <label class="text-danger"> (*)</label>:
							<input class="form-control" name="hoTen" >
							<c:if test="${!empty sessionScope.hoTen }">
								<div class="alert alert-danger">
									${sessionScope.hoTen}
									<c:remove var="hoTen" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group mt-2">
							<label>Avatar:</label>
							<input type="file" class="form-control mt-2" name="avatar">
						</div>
						Giới tính <label class="text-danger mt-2"> (*)</label>:
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gioiTinh" id="inlineRadio1" value="1" checked >
						  <label class="form-check-label" for="inlineRadio1">Nam</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gioiTinh" id="inlineRadio2" value="0" >
						  <label class="form-check-label" for="inlineRadio2">Nữ</label>
						</div>
						<br>
						
						<div class="form-group mt-2">
								Số điện thoại <label class="text-danger"> (*)</label>:
								<input class="form-control" name="sdt" >
								<c:if test="${!empty sessionScope.sdt }">
									<div class="alert alert-danger">
										${sessionScope.sdt}
										<c:remove var="sdt" scope="session"/>
									</div>
								</c:if>
								<c:if test="${!empty sessionScope.sdtreg }">
									<div class="alert alert-danger">
										${sessionScope.sdtreg}
										<c:remove var="sdtreg" scope="session"/>
									</div>
								</c:if>
						</div>
						<div class="form-group mt-2">
							Email <label class="text-danger"> (*)</label>:
							<input class="form-control" name="email" >
							<c:if test="${!empty sessionScope.email }">
								<div class="alert alert-danger">
									${sessionScope.email}
									<c:remove var="email" scope="session"/>
								</div>
							</c:if>
							<c:if test="${!empty sessionScope.emailPt }">
								<div class="alert alert-danger">
									${sessionScope.emailPt}
									<c:remove var="emailPt" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							Password <label class="text-danger"> (*)</label>:
							<input type="password" class="form-control" name="password" >
							<c:if test="${!empty sessionScope.password }">
								<div class="alert alert-danger">
									${sessionScope.password}
									<c:remove var="password" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							Địa chỉ <label class="text-danger"> (*)</label>:
							<input class="form-control" name="diaChi">
							<c:if test="${!empty sessionScope.diaChi }">
								<div class="alert alert-danger">
									${sessionScope.diaChi}
									<c:remove var="diaChi" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group mt-2 text-center">
							<button class="btn btn-primary ">Đăng ký</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-3"></div>
	</div>
</div>