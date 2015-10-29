package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Customer;
import model.Favor;
import model.Idle;
import model.Restaurant;
import model.Saler;
import model.Vip;

public class RestDao {
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public RestDao()
	{
		
	}
	
	public List<Restaurant> findAllRest() {
		
		Session session = sessionFactory.openSession();
		List<Restaurant> restList = session.createQuery("from Restaurant").list(); 
		session.close();
		return restList;
	}

	public Restaurant getRestById(Long rid) {
		Session session = sessionFactory.openSession();
		Restaurant buf =  (Restaurant) session.get(Restaurant.class, rid);
		session.close();
		return buf;
	}
	
	public List getRestFavors(Long rid) {
		Session session = sessionFactory.openSession();
		List<Favor> favors = session.createSQLQuery("select f.* from restaurants r natural join favors f where r.restId = :rid")
				.addEntity(Favor.class).setParameter("rid", rid)
				.list();
		session.close();
		return favors;
	}

	public List getRestIdles(Long rid) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from restaurants r natural join idles i where r.restId = :rid")
				.addEntity(Idle.class).setParameter("rid", rid)
				.list();
		session.close();
		return idles;
	}

	public void updateIdle(Idle idle) {
		
		Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
	    session.update(idle);
		tx.commit();
		session.close();
		
	}


	
	public void deleteIdle(Idle idle) {
		
	    Session session = sessionFactory.openSession();		
	    Transaction tx = session.beginTransaction();
	    session.delete(idle);		
		tx.commit();
		session.close();
	}

	public List searchRest(String rname) {
		Session session = sessionFactory.openSession();
		List rests = session.createSQLQuery("select r.* from restaurants r where r.restName like concat('%', :rname, '%')")
				.addEntity(Restaurant.class).setParameter("rname", rname)
				.list();
		session.close();
		return rests;
	}

	public List getCusRestIdles(Long rid, Long cid) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from restaurants r natural join idles i natural join customers c where c.cusId = :cid and r.restId = :rid")
				.addEntity(Idle.class).setParameter("rid", rid).setParameter("cid", cid)
				.list();
		session.close();
		return idles;
	}
	
	public List getCusRestIdlesLimit(Long rid, Long cid, int start, int offset) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from restaurants r natural join idles i natural join customers c where c.cusId = :cid and r.restId = :rid order by idleTime desc limit :start, :offset")
				.addEntity(Idle.class).setParameter("rid", rid).setParameter("cid", cid).setParameter("start", start).setParameter("offset", offset)
				.list();
		session.close();
		return idles;
	}
	
	public int getCusRestIdlesSize(Long rid, Long cid) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from restaurants r natural join idles i natural join customers c where c.cusId = :cid and r.restId = :rid")
				.addEntity(Idle.class).setParameter("rid", rid).setParameter("cid", cid)
				.list();
		session.close();
		return idles.size();
	}

	public List getRestVips(Long rid) {
		Session session = sessionFactory.openSession();
		List<Vip> vips = session.createSQLQuery("select v.* from restaurants r natural join vips v where r.restId = :rid")
				.addEntity(Vip.class).setParameter("rid", rid)
				.list();
		session.close();
		return vips;
	}
}
