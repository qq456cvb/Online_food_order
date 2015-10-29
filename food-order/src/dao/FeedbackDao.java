package dao;

import java.util.List;

import model.Feedback;
import model.Saler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FeedbackDao {
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public FeedbackDao()
	{
		
	}

	public void addFeedback(Feedback feedback) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(feedback);
		tx.commit();
		session.close();
	}
	
	public List getAllFb() {
		Session session = sessionFactory.openSession();
		List fbs = session.createSQLQuery("select fb.* from feedbacks fb").addEntity(Feedback.class).list(); 
		return fbs;
	}
}
