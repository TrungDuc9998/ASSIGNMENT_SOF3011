package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import Cookie.CookieUtils;
import DAO.CategoryDAO;
import DAO.InvoiceDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import DAO.UserProducDAO;
import Entity.Category;
import Entity.Invoice;
import Entity.Product;
import Entity.User;
import Entity.Userproduct;
import utils.EncryptUtil;

@MultipartConfig()
@WebServlet({
//	"/user/index",
//	"/user/login",
//	"/user/show",
//	"/user/edit",
//	"/user/update",
//	"/user/delete",
//	"/user/insert",
//	"/user/store",
//	"/user/register",
//	"/user/registerStore",
//	"/login2",
//	"/user/logout",
//	"/category/show",
//	"/category/insert",
//	"/category/store",
//	"/category/edit",
//	"/category/update",
//	"/category/delete",
//	"/product/detail",
//	"/product/show",
//	"/product/insert",
//	"/product/store",
//	"/product/edit",
//	"/product/update",
//	"/product/delete",
//	"/sp/card",
//	"/sp/store",
//	"/sp/delete",
//	"/invoice/store",
//	"/invoice/storee",
//	"/invoice/insert",
//	"/invoice/bill",
//	"/invoice/update",
//	"/invoice/cancel",
//	"/invoice/list",
//	"/invoice/oder",
//	"/invoice/order"
	
	
	
})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private CategoryDAO categoryDAO;
	private ProductDAO productDAO;
	private UserProducDAO userProductDao;
	private InvoiceDAO invoiceDAO;
    public IndexServlet() {
        super();
        this.userDAO=new UserDAO();
        this.categoryDAO=new CategoryDAO();
        this.productDAO=new ProductDAO();
        this.userProductDao=new UserProducDAO();
        this.invoiceDAO=new InvoiceDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			request.setAttribute("hoTen", "Đăng nhập");
		}else {
			request.setAttribute("hoTen", user.getHoTen());
			request.setAttribute("admin", user.getAdmin());
		}
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		
		
		String uri=request.getRequestURI();
		
		
		if(uri.contains("index")) {
			this.index(request,response);
		}else if(uri.contains("detail")) {
			this.detail(request,response);
		}else if(uri.contains("logout")) {
			this.logout(request,response);
		}else if(uri.contains("card")) {
			this.card(request,response);
		}else if(uri.contains("login2")) {
			this.login(request,response);
		}else if(uri.contains("list")) {
			this.listoder(request,response);
		}else if(uri.contains("register")) {
			this.register(request,response);
		}else if(uri.contains("oder")) {
			this.oder(request,response);
		}else if(uri.contains("order")) {
			this.oder1(request,response);
		}else if(uri.contains("show")) {
			this.show(request,response);
		}else if(uri.contains("edit")) {
			this.edit(request,response);
		}else if(uri.contains("bill")) {
			this.bill(request,response);
		}else if(uri.contains("delete")) {
			this.delete(request,response);
		}else if(uri.contains("insert")) {
			this.insert(request,response);
		}
		
		
			
		
		
		
		
		
		
		
		
		
	}
	
	private void register(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
			
			request.setAttribute("view", "/views/user/register.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}
	
	
	private void oder1(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		request.setAttribute("user", user.getDiaChi());
		
		request.setAttribute("view", "/views/invoice/oder1.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void oder(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		request.setAttribute("user", user.getDiaChi());
		
		request.setAttribute("view", "/views/invoice/order.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void bill(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			
		}else if(user.getAdmin()==0) {
			List<Invoice>list=this.invoiceDAO.findAll();
			System.out.println(list);
			request.setAttribute("listBill", list);
			request.setAttribute("view", "/views/invoice/bill.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
	}
	private void listoder(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			if(user==null) {
				
			}else {
				List<Invoice>list=this.invoiceDAO.findByTrangThai(Integer.valueOf(user.getId()));
				request.setAttribute("listBill", list);
				System.out.println(list.get(0).getThanhTien());
			}
			request.setAttribute("view", "/views/invoice/bill-user.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}
	
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product>list;
		String idStr=request.getParameter("id");
		if(idStr==null) {
			list=this.productDAO.findAll();
		}else {
			int id=Integer.valueOf(idStr);
			list=this.productDAO.findById(id);

			
		}
		request.setAttribute("listDH", list);
		
		List<Category>listcate=this.categoryDAO.findAll();
		request.setAttribute("ListCate", listcate);
		
		
		request.setAttribute("view", "/views/index.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("user");
		
		if(user.getAdmin()==0&&!user.equals("")) {
			if(uri.contains("category")) {
				request.setAttribute("view", "/views/category/create.jsp");
			}else if(uri.contains("user")) {
				request.setAttribute("view", "/views/user/create.jsp");
			}else if(uri.contains("product")) {
				List<Category> Category=this.categoryDAO.findAll();
				request.setAttribute("listCate", Category);
				request.setAttribute("view", "/views/product/create.jsp");
			}else if(uri.contains("invoice")) {
				

				request.setAttribute("view", "/views/invoice/card1.jsp");
			}
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
		
	}
	
	
	private void logout(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");	
		if (user != null) {  
		    session.invalidate();

			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		    return; 
		}
	}
	
	private void card(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		int tong=0;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			
		}else {
			int id=user.getId();
			System.out.println(id+"**");
			List<Userproduct> userproduct=this.userProductDao.findByIdUser_id(id);
			for (int i = 0; i < userproduct.size(); i++) {
				tong+=userproduct.get(i).getSoLuong()*userproduct.get(i).getProduct().getDonGia();
			}
			System.out.println(tong);
			request.setAttribute("userProduct", userproduct);
			request.setAttribute("tong", tong);
			
		}
		
		
		request.setAttribute("view", "/views/userProduct/card.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

		
	}
	
	

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		Product product=this.productDAO.findById1(id);
		request.setAttribute("product", product);
		request.setAttribute("view", "/views/product/detail.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/views/user/login.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri=request.getRequestURI();
	
		HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("user");
		
		if(!user.equals("")&&user.getAdmin()==0) {
			if(uri.contains("category")) {
				List<Category>listCate=this.categoryDAO.findAll();
				request.setAttribute("listCate", listCate);
				request.setAttribute("view", "/views/category/show.jsp");
			}else if(uri.contains("user")) {
				List<User>listUser=this.userDAO.findAll();
				request.setAttribute("listUser", listUser);
				request.setAttribute("view", "/views/user/show.jsp");
			}else if(uri.contains("product")){
				List<Category> Category=this.categoryDAO.findAll();
				request.setAttribute("listCate", Category);
				
				
				String idStr=request.getParameter("cate_id");
			
				
				if(idStr==null) {
					
				}else {
					int id=Integer.valueOf(idStr);
					List<Product>listPro=this.productDAO.findById(id);
					request.setAttribute("listPro", listPro);
				}
				
				request.setAttribute("view", "/views/product/show.jsp");
			}
			
			
			
			
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
		
		
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		if(u.getAdmin()==0&&!u.equals("")) {
			if(uri.contains("category")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				Category cate=this.categoryDAO.findById(id);
				request.setAttribute("cate", cate);
				request.setAttribute("view", "/views/category/edit.jsp");
			}else if(uri.contains("user")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				User user=this.userDAO.findById(id);
				request.setAttribute("user", user);
				request.setAttribute("view", "/views/user/edit.jsp");
			}else if(uri.contains("product")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				Product pro=this.productDAO.findById1(id);
				request.setAttribute("pro", pro);
				request.setAttribute("view", "/views/product/edit.jsp");
			}
			
			
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		

		
		
	}
	
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri=request.getRequestURI();

		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		if(u.getAdmin()==0&&!u.equals("")) {
			if(uri.contains("category")) {
				try {
					String idStr=request.getParameter("id");
					int id=Integer.valueOf(idStr);
					Category cate=this.categoryDAO.findById(id);
					this.categoryDAO.delete(cate);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			
				response.sendRedirect("/ASSIGNMENT_SOF3011/category/show");
			}else if(uri.contains("user")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				User user=this.userDAO.findById(id);
				this.userDAO.delete(user);
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/show");
			}else if(uri.contains("product")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				Product pro=this.productDAO.findById1(id);
				this.productDAO.delete(pro);
				response.sendRedirect("/ASSIGNMENT_SOF3011/product/show");
			}else if(uri.contains("sp")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				Userproduct pro=this.userProductDao.findById(id);
				this.userProductDao.delete(pro);
				response.sendRedirect("/ASSIGNMENT_SOF3011/sp/card");
			}else if(uri.contains("invoice")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				Invoice pro=this.invoiceDAO.findById(id);
				
				this.invoiceDAO.delete(pro);
				response.sendRedirect("/ASSIGNMENT_SOF3011/invoice/bill");
			}
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
			
		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		User u1=(User) session.getAttribute("user");
		if(u1.getAdmin()==0&&!u1.equals("")) {
			if(uri.contains("category")) {
				User u=(User) session.getAttribute("user");
				User user=this.userDAO.findById(u.getId());
				Category cate=new Category();
				try {
					BeanUtils.populate(cate, request.getParameterMap());
					cate.setUser(user);
					this.categoryDAO.update(cate);
					session.setAttribute("message", "Cập nhật thành công!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/category/show");
				} catch (Exception e) {
					session.setAttribute("error", "Cập nhật thất bại!");
					response.sendRedirect("//ASSIGNMENT_SOF3011/category/edit?id="+cate.getId());
					// TODO: handle exception
				}
			}else if(uri.contains("user")) {
				File dir=new File(request.getServletContext().getRealPath("/files"));
				if(!dir.exists()) {
					dir.mkdir();
				}
				
				Part photo=request.getPart("avatar");
				System.out.println("photo:"+photo);
				
				if(!photo.getSubmittedFileName().equals("")) {
					File photoFile=new File(dir,photo.getSubmittedFileName());
					System.out.println(photoFile);
					photo.write(photoFile.getAbsolutePath());
				}
				
				
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				User user=this.userDAO.findById(id);
				User u=new User();
				
				String emailPattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

				
				
					try {
						
						
						
						BeanUtils.populate(u, request.getParameterMap());
						u.setPassword(user.getPassword());

						if(photo.getSubmittedFileName().equals("")) {
							u.setAvatar(user.getAvatar());
						}else {
							u.setAvatar(photo.getSubmittedFileName());
						}
						
						if(u.getSdt().matches(reg)==false) {
							session.setAttribute("sdtreg", "Số điện thoại không đúng định dạng!");
							response.sendRedirect("/ASSIGNMENT_SOF3011/user/edit?id="+u.getId());
						}else if(u.getEmail().matches(emailPattern)==false) {
							session.setAttribute("emailPt", "email không đúng định dạng!");
							response.sendRedirect("/ASSIGNMENT_SOF3011/user/edit?id="+u.getId());
						}else {
							this.userDAO.update(u);
							session.setAttribute("message", "Cập nhật thành công!");
							response.sendRedirect("/ASSIGNMENT_SOF3011/user/show");
						}
						
						
						System.out.println("avatar:"+user.getAvatar());
						
						
					} catch (Exception e) {
						
						session.setAttribute("error", "Cập nhật thất bại!");
						response.sendRedirect("/ASSIGNMENT_SOF3011/user/edit?id="+user.getId());
					}
				
				
				
			}else if(uri.contains("product")) {
				File dir=new File(request.getServletContext().getRealPath("/files"));
				if(!dir.exists()) {
					dir.mkdir();
				}
				
				Part photo=request.getPart("img");
			
				if(photo.getSubmittedFileName().equals("")) {
					
				}else {
					File photoFile=new File(dir,photo.getSubmittedFileName());
					System.out.println(photoFile);
					photo.write(photoFile.getAbsolutePath());
				}
				
				
				String idCate=request.getParameter("id");
				int id=Integer.valueOf(idCate);
				Product pro=this.productDAO.findById1(id);
				Product product=new Product();
				String anh=request.getParameter("image");
				try {
					BeanUtils.populate(product, request.getParameterMap());
					
					System.out.println("id sản phẩm:"+product.getId());
					
					System.out.println("ảnh;"+anh);
					product.setCategoryId(pro.getCategoryId());
					
					if(photo.getSubmittedFileName().equals("")) {
						product.setImg(pro.getImg());
					}else {
						product.setImg(photo.getSubmittedFileName());
					}
					this.productDAO.update(product);
					session.setAttribute("message", "Cập nhật thành công!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/product/show");
				} catch (Exception e) {
					session.setAttribute("error", "Cập nhật thất bại!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/product/edit?id="+product.getId());
				}
			}else if(uri.contains("invoice")) {
				String idStr=request.getParameter("id");
				int id=Integer.valueOf(idStr);
				System.out.println(id);
				Invoice invoi=this.invoiceDAO.findById(id);
				try {
					System.out.println(invoi);
					invoi.setTrangThai(1);
					this.invoiceDAO.update(invoi);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				response.sendRedirect("/ASSIGNMENT_SOF3011/invoice/bill");
			}
			
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
		
		
		
	}

	
	

	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		if(uri.contains("category")) {
			
			User user=(User) session.getAttribute("user");
			
			
//			
//				User user=this.userDAO.findByEmail(email);
				User cate=this.userDAO.findById(user.getId());
				Category u=new Category();
				try {
					BeanUtils.populate(u, request.getParameterMap());

					System.out.println("họ tên lấy ra:"+u.getTen());
					u.setUser(cate);
					u=this.categoryDAO.insert(u);
					session.setAttribute("message", "Thêm mới thành công!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/category/show");
				} catch (Exception e) {
					session.setAttribute("error", "Thêm mới thất bại !");
					response.sendRedirect("/ASSIGNMENT_SOF3011/category/insert");
					// TODO: handle exception
				}
			
			
		}else if(uri.contains("user")) {
			
			
			File dir=new File(request.getServletContext().getRealPath("/files"));
			if(!dir.exists()) {
				dir.mkdir();
			}
			
			Part photo=request.getPart("avatar");
			
			if(photo.getSubmittedFileName().equals("")) {
				
			}else {
				File photoFile=new File(dir,photo.getSubmittedFileName());
				System.out.println(photoFile);
				photo.write(photoFile.getAbsolutePath());
			}
		
			
			
			
			User u=new User();
			try {
				BeanUtils.populate(u, request.getParameterMap());
				System.out.println("avatar:"+u.getAvatar());
				if(u.getAvatar()==null) {
					u.setAvatar("");
				}else {
					u.setAvatar(photo.getSubmittedFileName());
				}
				String encryted=EncryptUtil.encrypt(request.getParameter("password"));
				String emailPattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

				
				if(u.getHoTen().equals("")) {
					session.setAttribute("hoTen", "Họ tên không được trống!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getSdt().equals("")) {
					session.setAttribute("sdt", "Số điện thoại không được trống!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getEmail().equals("")) {
					session.setAttribute("email", "Email không được trống!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getPassword().equals("")) {
					session.setAttribute("password", "password không được trống!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getDiaChi().equals("")) {
					session.setAttribute("diaChi", "Địa chỉ không được trống!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getEmail().matches(emailPattern)==false) {
					session.setAttribute("emailPt", "Email không đúng định dạng!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}else if(u.getSdt().matches(reg)==false) {
					session.setAttribute("sdtreg", "Số điện thoại không đúng định dạng!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
				}
				
				else {
					u.setPassword(encryted);
					u=this.userDAO.insert(u);
					session.setAttribute("message", "Thêm mới thành công!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/user/show");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("error", "Thêm mới thất bại!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}
		}else if(uri.contains("product")) {
			
			File dir=new File(request.getServletContext().getRealPath("/files"));
			if(!dir.exists()) {
				dir.mkdir();
			}
			
			Part photo=request.getPart("img");
			if(photo.getSubmittedFileName().equals("")) {
				session.setAttribute("img", "ảnh sản phẩm không được trống");
				response.sendRedirect("/ASSIGNMENT_SOF3011/product/insert");
				
			}else {
				
				
				File photoFile=new File(dir,photo.getSubmittedFileName());
				System.out.println(photoFile);
				photo.write(photoFile.getAbsolutePath());
				
				String idStr=request.getParameter("cate_id");
				int id=Integer.valueOf(idStr);
				Category cate=this.categoryDAO.findById(id);
				
				Product pro=new Product();
				try {
					BeanUtils.populate(pro, request.getParameterMap());
					
					if(pro.getTen().equals("")||String.valueOf(pro.getDonGia()).equals("")||pro.getKichThuoc().equals("")||pro.getMauSac().equals("")||String.valueOf(pro.getSoLuong()).equals("")) {
						session.setAttribute("trong", "Giá trị không được trống!");
						response.sendRedirect("/ASSIGNMENT_SOF3011/product/insert");
					}if(pro.getDonGia()<0||pro.getSoLuong()<0) {
						session.setAttribute("donGia", "Giá trị phải lớn hơn 0!");
						response.sendRedirect("/ASSIGNMENT_SOF3011/product/insert");
					}else {
						if(!photo.getSubmittedFileName().equals("")) {
							pro.setImg(photo.getSubmittedFileName());
						}
					
						pro.setCategoryId(cate);
						pro=this.productDAO.insert(pro);
						session.setAttribute("message", "Thêm mới thành công!");
						response.sendRedirect("/ASSIGNMENT_SOF3011/product/show");
					}
					
					
				
				} catch (Exception e) {
					session.setAttribute("error", "Thêm mới thất bại!");
					response.sendRedirect("/ASSIGNMENT_SOF3011/product/insert");
					// TODO: handle exception
				}
			}
		
			
			
			
			
		}else if(uri.contains("sp")) {
			
			
			HttpSession session1=request.getSession();
			User user=(User) session1.getAttribute("user");
			if(user==null) {
				System.out.println("user-null");
			}else {
				
				String id=request.getParameter("id");
//				System.out.println("id product gio hang:"+id);
//				String idPro=request.getParameter("products");
//				int IdPro=Integer.valueOf(idPro);
				Product pro=this.productDAO.findById1(Integer.valueOf(id));
				
				String slStr=request.getParameter("soLuong");
				
				Userproduct userPro=new Userproduct();
				userPro.setProduct(pro);
				userPro.setSoLuong(Integer.valueOf(slStr));
				userPro.setUser(user);
				userPro=this.userProductDao.insert(userPro);
				
			}
			
			
			
			response.sendRedirect("/ASSIGNMENT_SOF3011/sp/card");
			
		}else if(uri.contains("invoice")) {
			
			Invoice invoice=new Invoice();
			HttpSession session2=request.getSession();
			User user=(User) session2.getAttribute("user");
			String diaChi=request.getParameter("diaChi");
			if(user==null) {
				
			}else {
				int id=user.getId();
				
				
				List<Userproduct> userproduct=this.userProductDao.findByIdUser_id(id);
				for (Userproduct userproduct2 : userproduct) {
					
					if(userproduct2.getId()>0) {
						
						invoice.setProduct(userproduct2.getProduct());
						invoice.setSoLuong(userproduct2.getSoLuong());
						invoice.setThoiGian(new Date());
						invoice.setTrangThai(0);
						invoice.setUser(user);
						invoice.setDiaChi(diaChi);
						invoice.setThanhTien(userproduct2.getSoLuong()*userproduct2.getProduct().getDonGia());
						this.invoiceDAO.insert(invoice);
						
						invoice=new Invoice();
						this.userProductDao.delete(userproduct2);
//						user.setDiaChi(diaChi);
//						this.userDAO.update(user);
						
					}
					
										
				}
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
				
			}
		}
	}
	
	private void storee(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Invoice invoice=new Invoice();
		String diaChi=request.getParameter("diaChi");
		HttpSession session2=request.getSession();
		User user=(User) session2.getAttribute("user");
		
		String soLuong=request.getParameter("soLuong");
		try {
			invoice.setSoLuong(Integer.valueOf(soLuong));
			invoice.setTrangThai(0);
			invoice.setUser(user);
			invoice.setThoiGian(new Date());
			this.invoiceDAO.insert(invoice);
			user.setDiaChi(diaChi);
			this.userDAO.update(user);
			System.out.println("thêm thành công hoá đơn");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		
	}
	
	private void login2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email"),
			pwd = request.getParameter("password");
		
		
		
    		User user = this.userDAO.findByEmail(email);
    		boolean check = EncryptUtil.check(pwd, user.getPassword());

		if (check == true) {
			
			session.setAttribute("user", user);
			System.out.println("dang nhap thanh cong");
		
			
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		} else {
			System.out.println("that bai");
			response.sendRedirect("/ASSIGNMENT_SOF3011/login2");
		}
		
	}
	
	private void cancel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		System.out.println(id);
		Invoice invoi=this.invoiceDAO.findById(id);
		try {
			System.out.println("---  vào huỷ đơn hang ---");
			System.out.println(invoi);
			invoi.setTrangThai(2);
			this.invoiceDAO.update(invoi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(user.getAdmin()==0) {
			response.sendRedirect("/ASSIGNMENT_SOF3011/invoice/bill");
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/invoice/list");
		}
		
		
		
	
		
	}
	
	
	
	
	private void registerStore(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		HttpSession session=request.getSession();
		File dir=new File(request.getServletContext().getRealPath("/files"));
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		Part photo=request.getPart("avatar");
		
		
		if(photo.getSubmittedFileName().equals("")) {
			
		}else {
			File photoFile=new File(dir,photo.getSubmittedFileName());
			System.out.println(photoFile);
			photo.write(photoFile.getAbsolutePath());
			
		}
		
		User u=new User();
		try {
			BeanUtils.populate(u, request.getParameterMap());
			
			if(photo.getSubmittedFileName().equals("")) {
				u.setAvatar("");
			}else {
				u.setAvatar(photo.getSubmittedFileName());
			}
			u.setAdmin(1);
			String encryted=EncryptUtil.encrypt(request.getParameter("password"));
			String emailPattern="^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
			String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

			
			if(u.getHoTen().equals("")) {
				session.setAttribute("hoTen", "Họ tên không được trống!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getSdt().equals("")) {
				session.setAttribute("sdt", "Số điện thoại không được trống!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getEmail().equals("")) {
				session.setAttribute("email", "Email không được trống!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getPassword().equals("")) {
				session.setAttribute("password", "password không được trống!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getDiaChi().equals("")) {
				session.setAttribute("diaChi", "Địa chỉ không được trống!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getEmail().matches(emailPattern)==false) {
				session.setAttribute("emailPt", "Email không đúng định dạng!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}else if(u.getSdt().matches(reg)==false) {
				session.setAttribute("sdtreg", "Số điện thoại không đúng định dạng!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/insert");
			}
			else {
				u.setPassword(encryted);
				this.userDAO.insert(u);
				session.setAttribute("message", "Đăng ký thành công!");
				response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
			}
			
		} catch (Exception e) {
			session.setAttribute("error", "Đăng ký thất bại!");
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/register");
		}
	
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri=request.getRequestURI();
		if(uri.contains("update")) {
			this.update(request, response);
		}else if(uri.contains("store")) {
			this.store(request,response);
		}else if(uri.contains("login2")) {
			this.login2(request,response);
		}else if(uri.contains("cancel")) {
			this.cancel(request,response);
		}else if(uri.contains("registerStore")) {
			this.registerStore(request, response);
		}else if(uri.contains("storee")) {
			this.storee(request, response);
		}
		
	}

}



