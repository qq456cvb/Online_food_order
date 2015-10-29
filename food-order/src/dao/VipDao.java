package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Customer;
import model.Vip;
import model.Restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VipDao {
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public VipDao()
	{
		
	}
	
	private CustomDao cusDao;
	private RestDao restDao;

	public boolean addVip(Long vid) {
		Session session = sessionFactory.openSession();	
		Vip vip = (Vip) session.createSQLQuery("select v.* from vips v where vipId = :vid")
			.addEntity(Vip.class).setParameter("vid", vid)
			.uniqueResult();
		if (vip == null || vip.getStat() == 0) {
			return false;
		} else {
			vip.setStat(2);
		    Transaction tx = session.beginTransaction();
			session.update(vip);
			tx.commit();
			session.close();
			return true;
		}
	}
	
	public boolean isVip(Long cid, Long rid) {
		Session session = sessionFactory.openSession();	
		Vip vip = (Vip) session.createSQLQuery("select v.* from vips v where cusId = :cid and restId = :rid")
			.addEntity(Vip.class).setParameter("cid", cid).setParameter("rid", rid)
			.uniqueResult();
		if (vip == null || vip.getStat() != 2) {
			return false;
		}
		return true;
	}
	
	public boolean reqVip(Long cid, Long rid) {
		Session session = sessionFactory.openSession();	
		Vip vip = (Vip) session.createSQLQuery("select v.* from vips v where cusId = :cid and restId = :rid")
			.addEntity(Vip.class).setParameter("cid", cid).setParameter("rid", rid)
			.uniqueResult();
		if (vip != null) {
			vip.setStat(1);
			Transaction tx = session.beginTransaction();
			session.update(vip);
			tx.commit();
			session.close();
			return true;
		}
		vip = new Vip();
		vip.setStat(1);
		Customer cus = new Customer();
		cus = cusDao.getUserById(cid);
		Restaurant rest = new Restaurant();
		rest = restDao.getRestById(rid);
	    List cusVipList = new ArrayList();
	    cusVipList = cusDao.getCusVips(cid);
	    List restVipList = new ArrayList();
	    restVipList = restDao.getRestVips(rid);
	    Set cusVips = new HashSet();
	    Set restVips = new HashSet();
	    for (Iterator it = cusVipList.iterator();it.hasNext();) {
            cusVips.add(it.next());            
	    }
	    for (Iterator it = restVipList.iterator();it.hasNext();) {
            restVips.add(it.next());            
	    }
	    vip.setCus(cus);
	    vip.setRest(rest);
	    if (!cusVips.add(vip)) {
	    	return false;
	    }
	    if (!restVips.add(vip)) {
	    	return false;
	    }
	    cus.setVips(cusVips);
	    rest.setVips(restVips);    	
	    Transaction tx = session.beginTransaction();
		session.save(vip);
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

	public List getVipRest(Long cid) {
		Session session = sessionFactory.openSession();
		List<Restaurant> rests = session.createSQLQuery("select r.* from customers c natural join vips v natural join restaurants r where cusId = :cid and stat != 0")
				.addEntity(Restaurant.class).setParameter("cid", cid)
				.list();
		session.close();
		return rests;
	}
	

	public boolean removeVip(Long vid) {
		Session session = sessionFactory.openSession();	
		Vip vip = (Vip) session.createSQLQuery("select v.* from vips v where vipId = :vid")
			.addEntity(Vip.class).setParameter("vid", vid)
			.uniqueResult();
		if (vip == null) {
			return false;
		} else {
			vip.setStat(0);
		    Transaction tx = session.beginTransaction();
			session.update(vip);
			tx.commit();
			session.close();
			return true;
		}
	}
	
}
