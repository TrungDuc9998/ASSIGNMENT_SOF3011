package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;


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
	
	
	"/user/show",
	"/user/edit",
	"/user/update",
	"/user/delete",
	"/user/insert",
	"/user/store",
	
	

})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private CategoryDAO categoryDAO;
	private ProductDAO productDAO;
	private UserProducDAO userProductDao;
	private InvoiceDAO invoiceDAO;
    public UserServlet() {
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
		
		
		if(uri.contains("show")) {
			this.show(request,response);
		}else if(uri.contains("edit")) {
			this.edit(request,response);
		}else if(uri.contains("delete")) {
			this.delete(request,response);
		}else if(uri.contains("insert")) {
			this.insert(request,response);
		}
		
		
			
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("user");
		
		if(user.getAdmin()==0&&!user.equals("")) {	
			request.setAttribute("view", "/views/user/create.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
		}
		
		
	}
	

	
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	
		HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("user");
		
		if(!user.equals("")&&user.getAdmin()==0) {
			
				List<User>listUser=this.userDAO.findAll();
				request.setAttribute("listUser", listUser);
				request.setAttribute("view", "/views/user/show.jsp");

			
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		}
		
		
		
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		if(u.getAdmin()==0&&!u.equals("")) {
			
			String idStr=request.getParameter("id");
			int id=Integer.valueOf(idStr);
			User user=this.userDAO.findById(id);
			request.setAttribute("user", user);
			request.setAttribute("view", "/views/user/edit.jsp");
			
			
			
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		}
		

		
		
	}
	
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		if(u.getAdmin()==0&&!u.equals("")) {
			String idStr=request.getParameter("id");
			int id=Integer.valueOf(idStr);
			User user=this.userDAO.findById(id);
			this.userDAO.delete(user);
			response.sendRedirect("/ASSIGNMENT_SOF3011/user/show");		
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		}	
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		User u1=(User) session.getAttribute("user");
		if(u1.getAdmin()==0&&!u1.equals("")) {
			 if(uri.contains("user")) {
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
				
				
				
			}
			
		}else {
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		}
		
	}

	
	

	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			File dir=new File(request.getServletContext().getRealPath("/files"));
			if(!dir.exists()) {
				dir.mkdir();
			}
			Part photo=request.getPart("avatar");
			if(photo.getSubmittedFileName().equals("")) {
				
			}else if(!photo.getSubmittedFileName().equals("")){
				File photoFile=new File(dir,photo.getSubmittedFileName());
				System.out.println(photoFile);
				photo.write(photoFile.getAbsolutePath());
			}	
			User u=new User();
			try {
				BeanUtils.populate(u, request.getParameterMap());
				System.out.println("avatar store:"+u.getAvatar());
				 if(!photo.getSubmittedFileName().equals("")){
					u.setAvatar(photo.getSubmittedFileName());
				}else if(photo.getSubmittedFileName().equals("")){
					u.setAvatar("");
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
		
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri=request.getRequestURI();
		if(uri.contains("update")) {
			this.update(request, response);
		}else if(uri.contains("store")) {
			this.store(request,response);
		}
		
	}

}



