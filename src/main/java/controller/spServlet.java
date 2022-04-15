package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import DAO.UserProducDAO;
import Entity.Product;
import Entity.User;
import Entity.Userproduct;

/**
 * Servlet implementation class spServlet
 */
@WebServlet({
	"/sp/card",
	"/sp/store",
	"/sp/delete",
})
public class spServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserProducDAO userProductDao;
    public ProductDAO productDAO;
    public spServlet() {
        super();
        this.userProductDao=new UserProducDAO();
        this.productDAO=new ProductDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		if(uri.contains("card")) {
			this.card(request,response);
		}if(uri.contains("delete")) {
			this.delete(request,response);
		}
	}
	
	public void delete(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		Userproduct pro=this.userProductDao.findById(id);
		this.userProductDao.delete(pro);
		response.sendRedirect("/ASSIGNMENT_SOF3011/sp/card");
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

	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			
			
			HttpSession session1=request.getSession();
			User user=(User) session1.getAttribute("user");
			if(user==null) {
				System.out.println("user-null");
			}else {
				
				String id=request.getParameter("id");
				System.out.println("id"+id);
				Product pro=this.productDAO.findById1(Integer.valueOf(id));
				
				String slStr=request.getParameter("soLuong");
				int soLuong=Integer.valueOf(slStr);
				System.out.println("soLuong"+soLuong);
				
				Userproduct userPro=new Userproduct();
				userPro.setProduct(pro);
				userPro.setSoLuong(soLuong);
				userPro.setUser(user);
				userPro=this.userProductDao.insert(userPro);
				
			}
			
			
			
			response.sendRedirect("/ASSIGNMENT_SOF3011/sp/card");
			
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		if(uri.contains("store")) {
			this.store(request,response);
		}
	}

}
