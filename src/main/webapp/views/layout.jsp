<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang web bán quần áo</title>
<link rel="stylesheet" href="/ASSIGNMENT_SOF3011/css/bootstrap.min.css">
</head>
<style>

	body{
		font-family: Helvetica,Arial,Roboto,sans-serif;
	}
	
	.col-2 a{
		text-decoration:none;
		color:black;
	}
	
	* {
		  box-sizing: border-box;
		}
	

</style>

<body>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background:#ffba00;">
  <div class="container-fluid ms-5">
  	<a  href="/ASSIGNMENT_SOF3011/index">
  		 <img class="ms-5" src="/ASSIGNMENT_SOF3011/image/transparent_logo1.png">
  	</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-3" >
      
      	 <li>
        	<a class="nav-link active" href="/ASSIGNMENT_SOF3011/invoice/list">Danh sách đơn đặt</a>
        </li>
     	 
        
       
       	<li class="nav-item" style="margin-left: 650px">
       	 <a class="nav-link active" href="/ASSIGNMENT_SOF3011/login">
       	 <img alt="" src="/ASSIGNMENT_SOF3011/image/login.png"> ${hoTen}</a>
       	</li>
       	<c:if test="${admin==0}">
        	<li class="nav-item active dropdown ">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            <img alt="" src="/ASSIGNMENT_SOF3011/image/administrative-fine.png">
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		         
		          	<li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/user/show">Quản lý người dùng</a></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/user/insert">Thêm người dùng</a></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/category/show">Quản lý loại sản phẩm</a></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/category/insert">Thêm sản loại sản phẩm</a></li>
		            <li><hr class="dropdown-divider"></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/product/show">Quản lý sản phẩm</a></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/product/insert">Quản lý người dùng</a></li>
		            <li><a class="dropdown-item" href="/ASSIGNMENT_SOF3011/invoice/bill">Quản lý đơn hàng</a></li>
		             
	          </ul>
	        </li> 
        </c:if> 
         <li class="nav-item">
        	<c:if test="${hoTen=='Đăng nhập'}">
        		<a class="btn btn-danger ms-2" type="button" href="/ASSIGNMENT_SOF3011/register">Đăng ký</a>
        		
        		
        	</c:if>
        	<c:if test="${hoTen!='Đăng nhập'}">
        		<a class="btn btn-danger ms-2" type="button" href="/ASSIGNMENT_SOF3011/logout">Đăng xuất</a>
        		
        	</c:if> 
        </li>
        
      	 <li>
        	<a class="ms-4" href="/ASSIGNMENT_SOF3011/sp/card">
        		<img alt="" src="/ASSIGNMENT_SOF3011/image/shopping-cart.png">
        	</a>
        </li>
       
    
      </ul>
    </div>
  </div>
</nav>


<div>
	<jsp:include page="${view}"></jsp:include>
</div>
<div class="container-fluid footer bg-light mt-4">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-2 ">
			<h6 class="mt-3 fw-bold">TẢI ỨNG DỤNG CHỢ TỐT</h6>
			<div class="row">
				<div class="col-6">
					<img class="" src="/ASSIGNMENT_SOF3011/image/group-qr.jpeg">
				</div>
				<div class="col-6">
					<img class="" src="/ASSIGNMENT_SOF3011/image/ios.svg">
					<img class="mt-2" src="/ASSIGNMENT_SOF3011/image/android.svg">
					<img class="mt-2" src="/ASSIGNMENT_SOF3011/image/huawei_app_install.png">
				</div>
			</div>
		</div>
		<div class="col-2 " >
			<h6 class="mt-3 ms-3 fw-bold" >HỖ TRỢ KHÁCH HÀNG</h6>
			<a class="ms-3">Trung tâm trợ giúp</a>
			<br>
			<a class="ms-3">An toàn mua bán</a>
			<br>
			<a class=" ms-3">Quy định cần biết</a>
			<br>
			<a class="ms-3">Quy chế riêng tư</a>
		</div>
		<div class="col-2 ">
			<h6 class="mt-3 fw-bold">VỀ CHỢ TỐT</h6>
			<a href="https://trogiup.chotot.com/nguoi-ban/gioi-thieu-chotot-com/">Giới thiệu</a>
			<br>
			<a>Tuyển dụng</a>
			<br>
			<a>Truyền thông</a>
			<br>
			<a>Blog</a>
		</div>
		<div class="col-2 ">
			<h6 class="mt-3 fw-bold">LIÊN KẾT</h6>
			<div class="row">
				<div class="col-3">
					<a href="https://www.youtube.com/user/ChototVN">
						<img class="mt-2" src="/ASSIGNMENT_SOF3011/image/facebook.svg">
					</a>
					
				</div>
				<div class="col-3">
					<img class="mt-2" src="/ASSIGNMENT_SOF3011/image/youtube.svg">
				</div>
				<div class="col-3">
					<img class="mt-2" src="/ASSIGNMENT_SOF3011/image/linkedin.svg">
				</div>
				<div class="col-12">
					<img class="mt-3" src="/ASSIGNMENT_SOF3011/image/cerfiticate.png">
				</div>
			</div>
		</div>
		<div class="col-2"></div>
	</div>
	<hr>
	<div class="row text-center">
		<p class="fs-6">CÔNG TY TNHH CHỢ TỐT - Địa chỉ: Phòng 1808, Tầng 18, Mê Linh Point Tower, 02 Ngô Đức Kế, Phường Bến Nghé, Quận 1, TP Hồ Chí Minh</p>
		<p>Giấy chứng nhận đăng ký doanh nghiệp số 0312120782 do Sở Kế Hoạch và Đầu Tư TPHCM cấp ngày 11/01/2013</p>
		<p>Email: trogiup@chotot.vn - Tổng đài CSKH: 19003003 (1.000đ/phút)<p>
	</div>
</div>

	<script src="/ASSIGNMENT_SOF3011/js/jquery.min.js"></script>
	<script src="/ASSIGNMENT_SOF3011/js/popper.min.js"></script>
	<script src="/ASSIGNMENT_SOF3011/js/bootstrap.min.js"></script>
</body>
</html>