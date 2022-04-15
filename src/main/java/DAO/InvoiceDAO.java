package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Invoice;
import Entity.User;
import Entity.Userproduct;
import utils.JpaUtil;

public class InvoiceDAO {
	private EntityManager em;
	
	public InvoiceDAO() {
		this.em=JpaUtil.getEntityManager();
	}
	
	public Invoice findById(int id) {
		Invoice invoice=this.em.find(Invoice.class, id);
		return invoice;
	}
	
	
	public List<Invoice> findAll(){
		String jpql="SELECT obj FROM Invoice obj";
		TypedQuery<Invoice> query=this.em.createQuery(jpql, Invoice.class);
		List<Invoice>list=query.getResultList();
		return list;
	}
	
	public List<Invoice>  findByIdUser(int id) {
		String jpql = "SELECT obj FROM Invoice obj "
			+ "WHERE obj.user.id = :uid";
		TypedQuery<Invoice> query = this.em.createQuery(jpql,
				Invoice.class);
		query.setParameter("uid", id);
		return query.getResultList();
	}
	
	public List<Invoice>  findByTrangThai(int id) {
		String jpql = "SELECT obj FROM Invoice obj "
			+ "WHERE obj.trangThai=1 or obj.trangThai=0 or obj.trangThai=2 and obj.user.id = :uid";
		TypedQuery<Invoice> query = this.em.createQuery(jpql,
				Invoice.class);
		query.setParameter("uid", id);
		return query.getResultList();
	}
	
	public Invoice  TinhTongTienTheoTrangThai(int id) {
		String jpql = "SELECT obj.product.donGia  FROM Invoice obj where "
			+ "obj.product.id=:pid" ;
		TypedQuery<Invoice> query = this.em.createQuery(jpql,
				Invoice.class);
		query.setParameter("pid", id);
		Invoice list=query.getSingleResult();
		return list;
	}
	
	
	
	
	
	
	public Invoice insert(Invoice invoice) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(invoice);
			this.em.getTransaction().commit();
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Invoice update(Invoice invoice) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(invoice);
			this.em.getTransaction().commit();
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public Invoice delete(Invoice invoice) {
		try {
			this.em.getTransaction().begin();
			this.em.remove(invoice);
			this.em.getTransaction().commit();
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
}
