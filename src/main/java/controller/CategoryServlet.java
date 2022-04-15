package controller;


import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.BeanUtils;

import DAO.CategoryDAO;
import DAO.UserDAO;
import Entity.Category;

import Entity.User;



@WebServlet({
	"/category/show",
	"/category/insert",
	"/category/store",
	"/category/edit",
	"/category/update",
	"/category/delete",
})
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CategoryDAO categoryDAO;
	private UserDAO userDAO;
    public CategoryServlet() {
        super();
        this.categoryDAO=new CategoryDAO();
        this.userDAO=new UserDAO();
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
	String uri=request.getRequestURI();
	HttpSession session=request.getSession();
	
	User user=(User)session.getAttribute("user");
	
	if(user.getAdmin()==0&&!user.equals("")) {
		if(uri.contains("category")) {
			request.setAttribute("view", "/views/category/create.jsp");
		}
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}else {
		response.sendRedirect("/ASSIGNMENT_SOF3011/user/index");
	}
	
	
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
//			User user=this.userDAO.findByEmail(email);
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


	


