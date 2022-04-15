<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div class="container" style="margin-top:90px">
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
	<h4 class="text-center">QUẢN LÝ USER</h4>
	<a type="button" class="btn btn-warning" href="/ASSIGNMENT_SOF3011/user/insert">Thêm mới</a>
	<table class="table mt-2" >
	<thead>
		<tr>
			
			<th>Họ tên</th>
			<th>Giới tính</th>
			<th>Vai trò</th>
			<th>Sdt</th>
			<th>Email</th>
			<th>Hình ảnh</th>
			<th>Địa chỉ</th>
			<th colspan="2">thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listUser}" var="user">
			<tr>
				
				<td>${user.hoTen}</td>
				<td>
					<c:choose>
						<c:when test="${user.gioiTinh==1 }">Nam</c:when>
						<c:when test="${user.gioiTinh==0 }">Nữ</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${user.admin==1 }">user</c:when>
						<c:when test="${user.admin==0 }">admin</c:when>
					</c:choose>
				</td>
				
				<td>${user.sdt}</td>
				<td>${user.email}</td>
				<td>${user.avatar}</td>
				<td>${user.diaChi}</td>
				<td>
					<a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/user/edit?id=${user.id}">Sửa</a>
				</td>
				<td>
					<!-- Button trigger modal -->
					<button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}">
					  Xoá
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Xoá người dùng</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn xoá bản ghi này không?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
					        <a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/user/delete?id=${user.id}">Xoá</a>
					      </div>
					    </div>
					  </div>
					</div>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
