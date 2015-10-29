package dao;
import java.util.List;
import java.util.Set;

import idao.IUserDao;
import model.Customer;
import model.Favor;
import model.Idle;
import model.Restaurant;
import model.User;
import model.Vip;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class CustomDao implements IUserDao{
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public CustomDao()
	{
		
	}


	@Override
	public void add(Object o) {
		
		User user = (User) o;
		Customer cus = new Customer();
		cus.setId(user.getId());
		cus.setAge(user.getAge());
		cus.setEmail(user.getEmail());
		cus.setName(user.getName());
		cus.setPassWord(user.getPassWord());
		cus.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(cus);
		tx.commit();
		session.close();
	}
	
	@Override
	public void delete(Object o) {
		
		User user = (User) o;
		Customer cus = new Customer();
		cus.setId(user.getId());
		cus.setAge(user.getAge());
		cus.setEmail(user.getEmail());
		cus.setName(user.getName());
		cus.setPassWord(user.getPassWord());
		cus.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(cus);
		tx.commit();
		session.close();
	}

	@Override
	public Customer find(String userName) {
	
		Session session = sessionFactory.openSession();
		Customer cus = (Customer) session.createQuery(
                "select p from Customer as p where p.userName = :pusername").
                setParameter("pusername", userName).uniqueResult(); 
		session.close();
		return cus;
	}

	
	@Override
	public Customer getUserById(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Customer buf =  (Customer) session.get(Customer.class, id);
		session.close();
		return buf;
	}
	
	@Override
	public void update(Object o) {

		User user = (User) o;
		Customer cus = new Customer();
		cus.setId(user.getId());
		cus.setAge(user.getAge());
		cus.setEmail(user.getEmail());
		cus.setName(user.getName());
		cus.setPassWord(user.getPassWord());
		cus.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(cus);
		tx.commit();
		session.close();
	}
	
	public void setCusimg(Customer cus) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(cus);
		tx.commit();
		session.close();
	}

	public List getFavors(Long cid) {
		Session session = sessionFactory.openSession();
		List<Favor> favors = session.createSQLQuery("select f.* from customers c natural join favors f where c.cusId = :cid")
				.addEntity(Favor.class).setParameter("cid", cid)
				.list();
		session.close();
		return favors;
	}

	public List getCusIdles(Long cid) {
		Session session = sessionFactory.openSession();
		List<Idle> idles = session.createSQLQuery("select i.* from customers c natural join idles i where c.cusId = :cid")
				.addEntity(Idle.class).setParameter("cid", cid)
				.list();
		session.close();
		return idles;
	}

	public List getCusVips(Long cid) {
		Session session = sessionFactory.openSession();
		List<Vip> vips = session.createSQLQuery("select v.* from customers c natural join vips v where c.cusId = :cid")
				.addEntity(Vip.class).setParameter("cid", cid)
				.list();
		session.close();
		return vips;
	}
	

}