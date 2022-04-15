package controller;

import java.io.File;
import java.io.IOException;
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
import DAO.ProductDAO;
import Entity.Category;
import Entity.Invoice;
import Entity.Product;
import Entity.User;

/**
 * Servlet implementation class ProductServlet
 */
@MultipartConfig()
@WebServlet({
	"/product/detail",
	"/product/show",
	"/product/insert",
	"/product/store",
	"/product/edit",
	"/product/update",
	"/product/delete",
})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	public ProductDAO productDAO;
	public CategoryDAO categoryDAO;
    public ProductServlet() {
        super();
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		if(uri.contains("detail")) {
			this.detail(request,response);
		}else if(uri.contains("show")) {
			this.show(request,response);
		}else if(uri.contains("insert")) {
			this.insert(request,response);
		}else if(uri.contains("edit")) {
			this.edit(request,response);
		}else if(uri.contains("delete")) {
			this.delete(request,response);
		}
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> Category=this.categoryDAO.findAll();
		request.setAttribute("listCate", Category);
		request.setAttribute("view", "/views/product/create.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		Product pro=this.productDAO.findById1(id);
		this.productDAO.delete(pro);
		response.sendRedirect("/ASSIGNMENT_SOF3011/product/show");
	}
	
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		Product pro=this.productDAO.findById1(id);
		request.setAttribute("pro", pro);
		request.setAttribute("view", "/views/product/edit.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	
	
	public void show (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	
	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
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
	}
	
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
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
	}
		
		

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		if(uri.contains("store")) {
			this.store(request,response);
		}if(uri.contains("update")) {
			this.update(request,response);
		}
	}

}
