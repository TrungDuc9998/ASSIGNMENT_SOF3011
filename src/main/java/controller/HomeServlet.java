package controller;


import java.io.File;
import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;


import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Entity.Category;
import Entity.Product;
import Entity.User;
import utils.EncryptUtil;


@WebServlet({
	"/index",
	"/login",
	"/logout",
	"/register",
	
})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserDAO userDAO;
    public ProductDAO productDAO;
    public CategoryDAO categoryDAO;
    public HomeServlet() {
        super();
        this.userDAO=new UserDAO();
        this.productDAO=new ProductDAO();
        this.categoryDAO=new CategoryDAO();
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
		
		String uri=request.getRequestURI();
		if(uri.contains("logout")) {
			this.logout(request,response);
		}else if(uri.contains("login")) {
			this.login(request,response);
		}else if(uri.contains("register")) {
			this.register(request,response);
		}if(uri.contains("index")) {
			this.index(request, response);
		}
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
	
	private void register(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		request.setAttribute("view", "/views/user/register.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/views/user/login.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");	
		if (user != null) {  
		    session.invalidate();
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		    return; 
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		if(uri.contains("register")) {
			this.registerStore(request, response);
		}if(uri.contains("login")) {
			this.login2(request, response);
		}
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

			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		} else {
			System.out.println("that bai");
			response.sendRedirect("/ASSIGNMENT_SOF3011/login");
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
				response.sendRedirect("/ASSIGNMENT_SOF3011/index");
			}
			
		} catch (Exception e) {
			session.setAttribute("error", "Đăng ký thất bại!");
			response.sendRedirect("/ASSIGNMENT_SOF3011/register");
		}
	
		
		
	}
}
