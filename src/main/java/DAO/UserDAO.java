package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.User;
import utils.JpaUtil;

public class UserDAO {
	private EntityManager em;
	
	public UserDAO() {
		this.em=JpaUtil.getEntityManager();
	}
	
	public User insert(User user) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(user);
			this.em.getTransaction().commit();
			return user;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
	
	public User findByEmail(String email) {
		String jpql = "SELECT obj FROM User obj "
			+ "WHERE obj.email = :email";
		TypedQuery<User> query = this.em.createQuery(jpql,
				User.class);
		query.setParameter("email", email);
		
		return query.getSingleResult();
	}
	
	public List<User> findAll(){
		String jpql="SELECT obj FROM User obj";
		TypedQuery<User> query=this.em.createQuery(jpql, User.class);
		List<User>list=query.getResultList();
		return list;
	}
	
	public User findById(int id) {
		User entity=this.em.find(User.class, id);
		return entity;
	}
	
	public User update(User user) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(user);
			this.em.getTransaction().commit();
			return user;
		} catch (Exception e) {
			this.em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
	
	public User delete(User user) {
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
}
