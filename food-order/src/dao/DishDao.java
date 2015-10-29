package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Customer;
import model.Dish;
import model.Restaurant;
import model.User;

public class DishDao {
	
	private SessionFactory sessionFactory;  
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public DishDao()
	{
		
	}
	public Dish getDishById(Long did) {
		Session session = sessionFactory.openSession();
		Dish buf =  (Dish) session.get(Dish.class, did);
		session.close();
		return buf;
	}

	public void updateDish(Dish dish) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(dish);
		tx.commit();
		session.close();
		
	}

	public List<Dish> getDishes(Long rid) {
		Session session = sessionFactory.openSession();
		List<Dish> rdishes = session.createSQLQuery("select d.* from dishes d natural join menus m natural join restaurants r  where r.restId = :rid")
				.addEntity(Dish.class).setParameter("rid", rid)
				.list();
		session.close();
		return rdishes;
	}

	public void addDish(Dish dish) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dish);
		tx.commit();
		session.close();
		
	}

	public void deleteDish(Dish dish) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(dish);
		tx.commit();
		session.close();
		
	}

}
