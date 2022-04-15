package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.Product;
import Entity.User;
import Entity.Userproduct;
import utils.JpaUtil;

public class UserProducDAO {
	private EntityManager em;
	
	public UserProducDAO() {
		this.em=JpaUtil.getEntityManager();
	}
	
	public Userproduct insert(Userproduct Useproduct) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(Useproduct);
			this.em.getTransaction().commit();
			return Useproduct;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().commit();
			throw e;
			// TODO: handle exception
		}
	}
	
	public List<Userproduct> findAll(){
		String jpql="SELECT obj FROM Userproduct obj";
		TypedQuery<Userproduct> query=this.em.createQuery(jpql, Userproduct.class);
		List<Userproduct>list=query.getResultList();
		return list;
	}
	
	
	public Userproduct delete(Userproduct user) {
		try {
			this.em.getTransaction().begin();
			this.em.remove(user);
			this.em.getTransaction().commit();
			return user;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
	
	public Userproduct  findById(int id) {
		Userproduct entity=this.em.find(Userproduct.class, id);
		return entity;
	}
	
	
	public Userproduct TongGia(int id) {
		String jpql="SELECT obj.soLuong*obj.products.donGia FROM Userproduct obj where obj.id=:pid";
		TypedQuery<Userproduct>query=this.em.createQuery(jpql,Userproduct.class);
		query.setParameter("pid", id);
		Userproduct list=query.getSingleResult();
		return list;
	}
	
	
	
	public List<Userproduct>findByIdProduct(int id){
		String jpql="SELECT obj FROM Userproduct obj where obj.products.id=:pid";
		TypedQuery<Userproduct>query=this.em.createQuery(jpql,Userproduct.class);
		query.setParameter("pid", id);
		List<Userproduct>list=query.getResultList();
		return list;
	}
	
	public Userproduct findByIdProduct1(int id){
		String jpql="SELECT obj FROM Userproduct obj where obj.products.id=:pid";
		TypedQuery<Userproduct>query=this.em.createQuery(jpql,Userproduct.class);
		query.setParameter("pid", id);
		Userproduct list=query.getSingleResult();
		return list;
	}
	
	public List<Userproduct>findByIdUser_id(int id){
		String jpql="SELECT obj FROM Userproduct obj where obj.user.id=:pid";
		TypedQuery<Userproduct>query=this.em.createQuery(jpql,Userproduct.class);
		query.setParameter("pid", id);
		List<Userproduct>list=query.getResultList();
		return list;
	}
	
	
	
	
	

}
