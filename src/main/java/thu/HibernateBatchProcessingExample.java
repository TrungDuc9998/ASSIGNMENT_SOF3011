//package thu;
//
//
//
//
//import Entity.Product;
//import lombok.extern.log4j.Log4j2;
//import utils.HibernateUtils;
//import utils.JpaUtil;
//
//import javax.persistence.Persistence;
//import org.hibernate.Transaction;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.stat.Statistics;
//
//
//@Log4j2
//public class HibernateBatchProcessingExample {
//	public static void main(String[] args) {
//		Transaction tx =  null;
//		int batchSize = 5;
//		try(Session session = HibernateUtils.getSessionFactory().openSession() ) {
//			tx = session.beginTransaction();
//		//	for ( int i = 1; i <= 100_000; i++ ) {
//			for ( int i = 1; i <= 10; i++ ) {
//				Product  person = new Product();
//				person.setTen("Gavin_"+i);
//				
//				session.persist(person);
//				if ( i > 0 && i % batchSize == 0 ) {
//					System.out.println("flush and clear the session");
//		            //flush a batch of inserts and release memory
//		            session.flush();
//		            session.clear();
//		        }
//		    }
//			tx.commit();
//		} catch (Exception e) {
//			if(tx != null && tx.isActive())
//				tx.rollback();
//			throw e;
//		}
//	}
//}
