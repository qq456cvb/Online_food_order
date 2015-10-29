package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Dish;
import model.Idle;
import model.Order;
import model.OrderDish;
import model.Restaurant;

public class OrderDao {
private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public OrderDao()
	{
		
	}
	
	private IdleDao idleDao;
	private DishDao dishDao;
	
	public boolean addOrder(Long iid, String time) {
		Order order = new Order();
		order.setOrderTime(time);
		Idle idle = idleDao.getIdleById(iid);
		idle.setOrder(order);
		order.setIdle(idle);
		Session session = sessionFactory.openSession();	
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		session.close();
		return true;
	}
	
	public Order getOrderById(Long oid) {
		Session session = sessionFactory.openSession();
		Order buf =  (Order) session.get(Order.class, oid);
		session.close();
		return buf;
		
	}
	
	public Order getOrderByIid(Long iid) {
		Session session = sessionFactory.openSession();
		Order order = (Order) session.createSQLQuery("select o.* from orders o natural join idles i where i.idleId = :iid")
				.addEntity(Order.class).setParameter("iid", iid)
				.uniqueResult();
		session.close();
		return order;
	}
	public IdleDao getIdleDao() {
		return idleDao;
	}
	public void setIdleDao(IdleDao idleDao) {
		this.idleDao = idleDao;
	}
	
	public DishDao getDishDao() {
		return dishDao;
	}
	public void setDishDao(DishDao dishDao) {
		this.dishDao = dishDao;
	}
	public boolean addOrderDish(Long oid, Long did, Long num) {
		OrderDish orderDish = new OrderDish();
		orderDish.setDish(dishDao.getDishById(did));
		orderDish.setOrder(getOrderById(oid));
		orderDish.setNum(num);
		Session session = sessionFactory.openSession();	
		Transaction tx = session.beginTransaction();
		session.save(orderDish);
		tx.commit();
		session.close();
		return true;
	}

	public List getOrderDish(Long oid) {
		Session session = sessionFactory.openSession();
		List<Dish> dishes = (List) session.createSQLQuery("select d.* from orders o natural join orderdishes od natural join dishes d where o.orderId = :oid")
				.addEntity(Dish.class).setParameter("oid", oid)
				.list();
		session.close();
		return dishes;
	}

	public List getOrderByDate(Long rid, String date) {
		Session session = sessionFactory.openSession();
		List<Order> orders = (List) session.createSQLQuery("select o.* from orders o  where o.orderTime like concat(:date, '%') ")
				.addEntity(Order.class).setParameter("date", date)
				.list();
		for (int i = 0; i < orders.size(); i++) {
			if (((Order) orders.get(i)).getIdle().getRest().getId() != rid) {
				orders.remove(i);
			}
		}
		session.close();
		return orders;
	}

}
