package controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAO.CategoryDAO;
import DAO.InvoiceDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import DAO.UserProducDAO;
import Entity.Category;
import Entity.Invoice;

import Entity.User;
import Entity.Userproduct;



@WebServlet({
	"/invoice/store",
	"/invoice/storee",
	"/invoice/insert",
	"/invoice/bill",
	"/invoice/update",
	"/invoice/cancel",
	"/invoice/list",
	"/invoice/oder",
	"/invoice/order"
})
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryDAO categoryDAO;
    public InvoiceDAO invoiceDAO;
    public UserProducDAO userProductDao;
    public ProductDAO productDAO;
    public UserDAO userDAO;
    public InvoiceServlet() {
        super();
        this.categoryDAO=new CategoryDAO();
        this.userProductDao=new UserProducDAO();
        this.productDAO=new ProductDAO();
        this.userDAO=new UserDAO();
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
		if(uri.contains("delete")) {
			this.delete(request,response);
		}else if(uri.contains("insert")) {
			this.insert(request,response);
		}else if(uri.contains("order")) {
			this.oder1(request,response);
		}else if(uri.contains("oder")) {
			this.oder(request,response);
		}else if(uri.contains("bill")) {
			this.bill(request,response);
		}else if(uri.contains("list")) {
			this.listoder(request,response);
		}
	}
	
	
	
	
	private void listoder(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			
		}else {
			int id=Integer.valueOf(user.getId());
			System.out.println("id"+id);
			List<Invoice>list=this.invoiceDAO.findByTrangThai(id);
			request.setAttribute("listBill", list);
			System.out.println(list.get(0).getThanhTien());
		}
		request.setAttribute("view", "/views/invoice/bill-user.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	private void oder(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		request.setAttribute("user", user.getDiaChi());
		
		request.setAttribute("view", "/views/invoice/order.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> Category=this.categoryDAO.findAll();
		request.setAttribute("listCate", Category);
		request.setAttribute("view", "/views/product/create.jsp");
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
				response.sendRedirect("/ASSIGNMENT_SOF3011/index");
			}
			
		}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		Invoice pro=this.invoiceDAO.findById(id);	
		this.invoiceDAO.delete(pro);
		response.sendRedirect("/ASSIGNMENT_SOF3011/invoice/bill");
	}
	
	private void oder1(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			request.setAttribute("user", user.getDiaChi());
			
			request.setAttribute("view", "/views/invoice/oder1.jsp");
			request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		HttpSession session=request.getSession();
		User u1=(User) session.getAttribute("user");
		if(u1.getAdmin()==0&&!u1.equals("")) {
			 if(uri.contains("invoice")) {
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
			response.sendRedirect("/ASSIGNMENT_SOF3011/index");
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
	

	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		
		 if(uri.contains("invoice")) {
			
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

						
					}
					
										
				}
				response.sendRedirect("/ASSIGNMENT_SOF3011/index");
				
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
		
		response.sendRedirect("/ASSIGNMENT_SOF3011/index");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri=request.getRequestURI();
		if(uri.contains("update")) {
			this.update(request, response);
		}else if(uri.contains("store")) {
			this.store(request,response);
		}else if(uri.contains("cancel")) {
			this.cancel(request,response);
		}else if(uri.contains("storee")) {
			this.storee(request, response);
		}
	}

}
