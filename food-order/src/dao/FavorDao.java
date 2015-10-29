package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Customer;
import model.Favor;
import model.Restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FavorDao {
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public FavorDao()
	{
		
	}
	
	private CustomDao cusDao;
	private RestDao restDao;

	public boolean addFavor(Long cid, Long rid) {
		Session session = sessionFactory.openSession();	
		Favor favor = (Favor) session.createSQLQuery("select f.* from favors f where cusId = :cid and restId = :rid")
			.addEntity(Favor.class).setParameter("cid", cid).setParameter("rid", rid)
			.uniqueResult();
		if (favor != null) {
			favor.setStat(1);
			Transaction tx = session.beginTransaction();
			session.update(favor);
			tx.commit();
			session.close();
			return true;
		}
		favor = new Favor();
		favor.setStat(1);
		Customer cus = new Customer();
		cus = cusDao.getUserById(cid);
		Restaurant rest = new Restaurant();
		rest = restDao.getRestById(rid);
	    List cusFavorList = new ArrayList();
	    cusFavorList = cusDao.getFavors(cid);
	    List restFavorList = new ArrayList();
	    restFavorList = restDao.getRestFavors(rid);
	    Set cusFavors = new HashSet();
	    Set restFavors = new HashSet();
	    for (Iterator it = cusFavorList.iterator();it.hasNext();) {
            cusFavors.add(it.next());            
	    }
	    for (Iterator it = restFavorList.iterator();it.hasNext();) {
            restFavors.add(it.next());            
	    }
	    favor.setCus(cus);
	    favor.setRest(rest);
	    if (!cusFavors.add(favor)) {
	    	return false;
	    }
	    if (!restFavors.add(favor)) {
	    	return false;
	    }
	    cus.setFavors(cusFavors);
	    rest.setFavors(restFavors);    	
	    Transaction tx = session.beginTransaction();
		session.save(favor);
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

	public List getFavorRest(Long cid) {
		Session session = sessionFactory.openSession();
		List<Restaurant> rests = session.createSQLQuery("select r.* from customers c natural join favors f natural join restaurants r where cusId = :cid and stat != 0")
				.addEntity(Restaurant.class).setParameter("cid", cid)
				.list();
		session.close();
		return rests;
	}
	
	public boolean findFavor(Long cid, Long rid) {
		Session session = sessionFactory.openSession();
		List<Favor> favors = session.createSQLQuery("select f.* from favors f where cusId = :cid and restId = :rid and stat != 0")
				.addEntity(Favor.class).setParameter("cid", cid).setParameter("rid", rid)
				.list();
		session.close();
		if (favors.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean removeFavor(Long cid, Long rid) {
		Session session = sessionFactory.openSession();	
		Favor favor = (Favor) session.createSQLQuery("select f.* from favors f where cusId = :cid and restId = :rid")
			.addEntity(Favor.class).setParameter("cid", cid).setParameter("rid", rid)
			.uniqueResult();
		if (favor == null || favor.getStat() == 0) {
			return false;
		} else {
			favor.setStat(0);
		    Transaction tx = session.beginTransaction();
			session.update(favor);
			tx.commit();
			session.close();
			return true;
		}
	}
	
}
