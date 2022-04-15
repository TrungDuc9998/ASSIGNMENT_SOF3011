<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="container" style="margin-top:90px">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<div class="card">
			<div class="row mt-3">
				<div class="col-6 ">
					<h5 class="ms-3">CẬP NHẬT USER</h5>
					<p class="ms-3">Hãy thay đổi thông tin cần thiết</p>
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
					<form action="/ASSIGNMENT_SOF3011/user/update?id=${user.id}" method="post" enctype="multipart/form-data">
						<div class="text-center">
							<img class="rounded-circle" width="140px" height="140px" src="/ASSIGNMENT_SOF3011/files/${user.avatar}">
						</div>
						<div class="form-group">
							<label>Họ tên:</label>
							<input class="form-control" name="hoTen" value="${user.hoTen}">
						</div>
						
						<div  class="mt-2">
							<label>Avatar:</label>
							<input type="file" name="avatar" class="form-control mt-1" >
						</div>
						
						<label class="mt-2">Giới tính:</label>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gioiTinh" id="inlineRadio1" value="1" ${user.gioiTinh==1?"checked":"" }>
						  <label class="form-check-label" for="inlineRadio1">Nam</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gioiTinh" id="inlineRadio2" value="0" ${user.gioiTinh==0?"checked":""}>
						  <label class="form-check-label" for="inlineRadio2">Nữ</label>
						</div>
						<div class="form-group">
							<label>Số điện thoại:</label>
							<input class="form-control" name="sdt" value="${user.sdt}">
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
						Phân quyền <label class="text-danger"> (*)</label>:
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="admin" id="inlineRadio1" value="0"  ${user.admin==0?"checked":""}>
						  <label class="form-check-label" for="inlineRadio1">Admin</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="admin" id="inlineRadio2" value="1" ${user.admin==1?"checked":""}>
						  <label class="form-check-label" for="inlineRadio2">User</label>
						</div>
						<div class="form-group">
							<label>Email:</label>
							<input class="form-control" name="email" value="${user.email}">
							<c:if test="${!empty sessionScope.emailPt }">
								<div class="alert alert-danger">
									${sessionScope.emailPt}
									<c:remove var="emailPt" scope="session"/>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label>Địa chỉ:</label>
							<input class="form-control" name="diaChi" value="${user.diaChi}">
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