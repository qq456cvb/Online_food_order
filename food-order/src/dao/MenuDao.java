package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Dish;
import model.Idle;
import model.Menu;
import model.Order;
import model.Restaurant;

public class MenuDao {
private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public MenuDao()
	{
		
	}
	
	private RestDao restDao;
	
	public List getMenuDish(Long rid) {
		Session session = sessionFactory.openSession();
		List<Dish> menuDishes = session.createSQLQuery("select d.* from restaurants r natural join menus m natural join dishes d where r.restId = :rid")
				.addEntity(Dish.class).setParameter("rid", rid)
				.list();
		session.close();
		return menuDishes;
	}
	
	public RestDao getRestDao() {
		return restDao;
	}
	public void setRestDao(RestDao restDao) {
		this.restDao = restDao;
	}

	public Menu getMenuByRid(Long rid) {
		Session session = sessionFactory.openSession();
		Menu menu = (Menu) session.createSQLQuery("select m.* from restaurants r natural join menus m where r.restId = :rid")
				.addEntity(Menu.class).setParameter("rid", rid)
				.uniqueResult();
		session.close();
		return menu;
	}

	public void addMenu(Long rid, Menu menu) {
		Restaurant rest = restDao.getRestById(rid);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(menu);
		rest.setMenu(menu);
		session.update(rest);
		tx.commit();
		session.close();
		
		
	}
}
