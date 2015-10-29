package dao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import idao.IUserDao;
import model.Restaurant;
import model.Saler;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class SalerDao implements IUserDao{
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public SalerDao()
	{
		
	}


	@Override
	public void add(Object o) {
		
		User user = (User) o;
		Saler saler = new Saler();
		saler.setId(user.getId());
		saler.setAge(user.getAge());
		saler.setEmail(user.getEmail());
		saler.setName(user.getName());
		saler.setPassWord(user.getPassWord());
		saler.setUserName(user.getUserName());
		saler.setStat(0);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(saler);
		tx.commit();
		session.close();
	}
	
	@Override
	public void delete(Object o) {
		
		User user = (User) o;
		Saler saler = new Saler();
		saler.setId(user.getId());
//		saler.setAge(user.getAge());
//		saler.setEmail(user.getEmail());
//		saler.setName(user.getName());
//		saler.setPassWord(user.getPassWord());
//		saler.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(saler);
		tx.commit();
		session.close();
	}

	@Override
	public Saler find(String userName) {
	
		Session session = sessionFactory.openSession();
		Saler saler = (Saler) session.createQuery(
                "select p from Saler as p where p.userName = :pusername").
                setParameter("pusername", userName).uniqueResult(); 
		session.close();
		return saler;
	}
	
	@Override
	public Saler getUserById(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Saler buf =  (Saler) session.get(Saler.class, id);
		session.close();
		return buf;
	}

	@Override
	public void update(Object o) {

		Saler saler = (Saler) o;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(saler);
		tx.commit();
		session.close();
	}

		public List findAllSaler() {
		
		Session session = sessionFactory.openSession();
		List<Saler> salerList = session.createSQLQuery("select s.* from salers s").addEntity(Saler.class).list(); 
		session.close();
		return salerList;
	}

		public void addRest(Long sid, Restaurant rest) {
			
			Saler saler = getUserById(sid);
			List lists = new ArrayList();
		    lists = getOwnRest(sid);
		    Set rests = new HashSet();
		    for (Iterator it =lists.iterator();it.hasNext();) {
	            rests.add(it.next());            
		    }
			rest.setOwner(saler);
		    rests.add(rest);
		    saler.setRests(rests);
		    Session session = sessionFactory.openSession();		
		    Transaction tx = session.beginTransaction();
			session.save(rest);
			tx.commit();
			session.close();
		}
		
		public void deleteRest(Long sid, Restaurant rest) {
			
			Saler saler = getUserById(sid);
			List lists = new ArrayList();
		    lists = getOwnRest(sid);
		    Set rests = new HashSet();
		    for (Iterator it =lists.iterator();it.hasNext();) {
	            rests.add(it.next());            
		    }
		    rests.remove(rest);
		    saler.setRests(rests);
		    Session session = sessionFactory.openSession();		
		    Transaction tx = session.beginTransaction();
		    session.delete(rest);		
			tx.commit();
			session.close();
		}
		
		public void updateRest(Restaurant rest) {
			
		    Session session = sessionFactory.openSession();		
		    Transaction tx = session.beginTransaction();
		    session.update(rest);
			tx.commit();
			session.close();
		}

		public List getOwnRest(Long sid) {
			Session session = sessionFactory.openSession();
			List<Restaurant> ownrests = session.createSQLQuery("select r.* from restaurants r natural join salers s where s.salerId = :sid")
					.addEntity(Restaurant.class).setParameter("sid", sid)
					.list();
			session.close();
			return ownrests;
		}

}