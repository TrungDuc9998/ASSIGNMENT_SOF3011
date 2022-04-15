<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
	<h4 class="text-center">QUẢN LÝ CATEGORY</h4>
	<a type="button" class="btn btn-success" href="/ASSIGNMENT_SOF3011/category/insert">Thêm mới</a>
	<table class="table mt-2" >
	<thead>
		<tr>
			<th>Id</th>
			<th>Tên danh mục</th>
			<th>Người quản lý</th>
			
			<th colspan="2">thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listCate}" var="cate">
			<tr>
				<td>${cate.id}</td>
				<td>${cate.ten}</td>
				<td>${cate.user.hoTen}</td>	
				<td>
					<a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/category/edit?id=${cate.id}">Sửa</a>
				</td>
				<td>
					<button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${cate.id}">
					  Xoá
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="exampleModal${cate.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Xoá loại sản phẩm</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn xoá bản ghi này không?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
					        <a type="button" class="btn btn-primary" href="/ASSIGNMENT_SOF3011/category/delete?id=${cate.id}">Xoá</a>
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
