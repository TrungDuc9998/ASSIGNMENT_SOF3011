package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Category;
import Entity.Product;
import Entity.User;
import utils.JpaUtil;

public class ProductDAO {
	private EntityManager em;
	
	public ProductDAO() {
		this.em=JpaUtil.getEntityManager();
	}
	
	
	public List<Product> findAll(){
		String jpql="SELECT obj FROM Product obj";
		TypedQuery<Product> query=this.em.createQuery(jpql, Product.class);
		List<Product>list=query.getResultList();
		return list;
	}
	
	
	
	
	
	public List<Product> findById(int id) {
		String jpql="SELECT obj FROM Product obj where obj.category.id=:cid";
		TypedQuery<Product>query=this.em.createQuery(jpql,Product.class);
		query.setParameter("cid", id);
		List<Product>list=query.getResultList();
		return list;
		
	
	}
	
	public Product delete(Product product) {
		try {
			this.em.getTransaction().begin();
			this.em.remove(product);
			this.em.getTransaction().commit();
			return product;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		}
	}
	
	
	public Product insert(Product product) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(product);
			this.em.getTransaction().commit();
			return product;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		}
	}
	
	public Product update(Product product) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(product);
			this.em.getTransaction().commit();
			return product;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			
		}
	}
	
	public Product findById1(int id) {
		Product cate=this.em.find(Product.class, id);
		return cate;
	}
}
