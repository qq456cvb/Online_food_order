package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Customer;
import model.Favor;
import model.Idle;
import model.Restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IdleDao {

	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public IdleDao()
	{
		
	}
	
	private CustomDao cusDao;
	private RestDao restDao;
	
	public List<Idle> findAllIdle() {
		
		Session session = sessionFactory.openSession();
		List<Idle> idleList = session.createQuery("from Idle").list(); 
		session.close();
		return idleList;
	}

	public Idle getIdleById(Long iid) {
		Session session = sessionFactory.openSession();
		Idle buf =  (Idle) session.get(Idle.class, iid);
		session.close();
		return buf;
	}

	public boolean addIdle(Idle idle) {
		Customer cus = new Customer();
		cus = idle.getCus();
		Restaurant rest = new Restaurant();
		rest = idle.getRest();
	    List cusIdleList = new ArrayList();
	    cusIdleList = cusDao.getCusIdles(cus.getId());
	    List restIdleList = new ArrayList();
	    restIdleList = restDao.getRestIdles(rest.getId());
	    Set cusIdles = new HashSet();
	    Set restIdles = new HashSet();
	    for (Iterator it = cusIdleList.iterator();it.hasNext();) {
            cusIdles.add(it.next());            
	    }
	    for (Iterator it = restIdleList.iterator();it.hasNext();) {
            restIdles.add(it.next());            
	    }
	    idle.setCus(cus);
	    idle.setRest(rest);
	    if (!cusIdles.add(idle)) {
	    	return false;
	    }
	    if (!restIdles.add(idle)) {
	    	return false;
	    }
	    cus.setIdles(cusIdles);
	    rest.setIdles(restIdles);
	    Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
		session.save(idle);
		tx.commit();
		session.close();
		return true;
		
	}
	
	public RestDao getRestDao() {
		return restDao;
	}
	public void setRestDao(RestDao restDao) {
		this.restDao = restDao;
	}
	public CustomDao getCusDao() {
		return cusDao;
	}
	public void setCusDao(CustomDao cusDao) {
		this.cusDao = cusDao;
	}

	public void acceptIdle(Long iid) {
		Idle idle = getIdleById(iid);
		idle.setStatus(2);
		Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
		session.update(idle);
		tx.commit();
		session.close();
		
	}
	
	public void denyIdle(Long iid) {
		Idle idle = getIdleById(iid);
		idle.setStatus(3);//3¾Ü¾ø
		Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
		session.update(idle);
		tx.commit();
		session.close();
		
	}

	public List getCusIdles(Long cid) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from customers c natural join idles i where c.cusId = :cid")
				.addEntity(Idle.class).setParameter("cid", cid)
				.list();
		session.close();
		return idles;
	}

	public void finishIdle(Long iid) {
		Idle idle = getIdleById(iid);
		idle.setStatus(1);//1Íê³É
		Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
		session.update(idle);
		tx.commit();
		session.close();
		
	}
	
	

}
