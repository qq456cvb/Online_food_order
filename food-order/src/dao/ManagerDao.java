package dao;
import java.util.List;

import idao.IUserDao;
import model.Manager;
import model.Saler;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class ManagerDao implements IUserDao{
	
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public ManagerDao()
	{
		
	}


	@Override
	public void add(Object o) {
		
		User user = (User) o;
		Manager mgr = new Manager();
		mgr.setId(user.getId());
		mgr.setAge(user.getAge());
		mgr.setEmail(user.getEmail());
		mgr.setName(user.getName());
		mgr.setPassWord(user.getPassWord());
		mgr.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(mgr);
		tx.commit();
		session.close();
	}
	
	@Override
	public void delete(Object o) {
		
		User user = (User) o;
		Manager mgr = new Manager();
		mgr.setId(user.getId());
		mgr.setAge(user.getAge());
		mgr.setEmail(user.getEmail());
		mgr.setName(user.getName());
		mgr.setPassWord(user.getPassWord());
		mgr.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(mgr);
		tx.commit();
		session.close();
	}

	@Override
	public Manager find(String userName) {
	
		Session session = sessionFactory.openSession();
		Manager mgr = (Manager) session.createQuery(
                "select p from Manager as p where p.userName = :pusername").
                setParameter("pusername", userName).uniqueResult(); 
		session.close();
		return mgr;
	}

	@Override
	public Manager getUserById(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Manager buf =  (Manager) session.get(Manager.class, id);
		session.close();
		return buf;
	}
	
	@Override
	public void update(Object o) {

		User user = (User) o;
		Manager mgr = new Manager();
		mgr.setId(user.getId());
		mgr.setAge(user.getAge());
		mgr.setEmail(user.getEmail());
		mgr.setName(user.getName());
		mgr.setPassWord(user.getPassWord());
		mgr.setUserName(user.getUserName());
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(mgr);
		tx.commit();
		session.close();
	}



}