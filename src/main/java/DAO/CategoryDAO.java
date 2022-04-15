package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Category;
import Entity.User;
import utils.JpaUtil;

public class CategoryDAO {
private EntityManager em;
	
	public CategoryDAO() {
		this.em=JpaUtil.getEntityManager();
	}
	
	
	
	
	public Category insert(Category cate) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(cate);
			this.em.getTransaction().commit();
			return cate;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
	
	
	public Category findById(int id) {
		Category cate=this.em.find(Category.class, id);
		return cate;
	}
	
	public List<Category>findCate(int id){
		String jpql="SELECT obj FROM Category obj where obj.user.id=:uid";
		TypedQuery<Category>query=this.em.createQuery(jpql,Category.class);
		query.setParameter("uid", id);
		return query.getResultList();
	}
	
	
	public Category update(Category cate) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(cate);
			this.em.getTransaction().commit();
			return cate;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
	
	public List<Category>findAll(){
		String jpql="SELECT obj FROM Category obj";
		TypedQuery<Category>query=this.em.createQuery(jpql,Category.class);
		List<Category>list=query.getResultList();
		return list;
	}
	
	
	public List<Category>findDongHo(){
		String jpql="SELECT obj FROM Category obj where obj.ten=N'nuoc hoa'";
		TypedQuery<Category>query=this.em.createQuery(jpql,Category.class);
		List<Category>list=query.getResultList();
		return list;
	}
	
	
	public Category delete(Category cate) {
		try {
			this.em.getTransaction().begin();
			this.em.remove(cate);
			this.em.getTransaction().commit();
			return cate;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
}
