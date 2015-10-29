package dao;

import java.util.List;

import model.Comment;
import model.Feedback;
import model.Idle;
import model.Saler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CommentDao {
	private SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public CommentDao()
	{
		
	}

	public void addComment(Comment cm) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(cm);
		tx.commit();
		session.close();
	}
	
	public List getAllCm() {
		Session session = sessionFactory.openSession();
		List cms = session.createSQLQuery("select cm.* from comments cm").addEntity(Comment.class).list(); 
		return cms;
	}
	
	public List getCmByRid(Long rid){
		Session session = sessionFactory.openSession();
		List<Comment> cms = session.createSQLQuery("select cm.* from comments cm natural join restaurants r where r.restId = :rid")
				.addEntity(Comment.class).setParameter("rid", rid)
				.list();
		session.close();
		return cms;
	}
	
	public List getCmByCid(Long cid){
		Session session = sessionFactory.openSession();
		List<Comment> cms = session.createSQLQuery("select cm.* from comments cm natural join customers c where c.cusId = :cid")
				.addEntity(Comment.class).setParameter("cid", cid)
				.list();
		session.close();
		return cms;
	}
	
	public List getCmByRidCid(Long rid, Long cid) {
		Session session = sessionFactory.openSession();
		List<Comment> cms = session.createSQLQuery("select cm.* from comments cm natural join customers c natural join restaurants r where c.cusId = :cid and r.restId = :rid")
				.addEntity(Comment.class).setParameter("cid", cid).setParameter("rid", rid)
				.list();
		session.close();
		return cms;
	}
	
	public List getCmByRidLimit(Long rid, int start, int offset) {
		Session session = sessionFactory.openSession();
		List<Comment> cms = session.createSQLQuery("select cm.* from comments cm natural join restaurants r where r.restId = :rid order by time desc limit :start, :offset")
				.addEntity(Comment.class).setParameter("rid", rid).setParameter("start", start).setParameter("offset", offset)
				.list();
		session.close();
		return cms;
	}
	
}
