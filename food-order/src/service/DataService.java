package service;

import java.util.List;

import model.Comment;
import model.Feedback;
import dao.CommentDao;
import dao.FeedbackDao;

public class DataService {
	
	private FeedbackDao fbDao;
	private CommentDao cmDao;
	
	public List getCmByRidLimit(Long rid, int start, int offset) {
		
		return cmDao.getCmByRidLimit(rid, start, offset);
	}
	
	public void addFeedback(Feedback feedback) {
		
		fbDao.addFeedback(feedback);
	}
	
	public List getAllFb() {
		return fbDao.getAllFb();
	}
	
	public void addComment(Comment cm) {
		cmDao.addComment(cm);
	}
	
	public List getAllCm() {
		return cmDao.getAllCm();
	}
	
	public List getCmByRid(Long rid) {
		return cmDao.getCmByRid(rid);
	}
	
	public List getCmByCid(Long cid) {
		return cmDao.getCmByCid(cid);
	}
	
	public List getCmByRidCid(Long rid, Long cid) {
		return cmDao.getCmByRidCid(rid, cid);
	}
	
	public FeedbackDao getFbDao() {
		return fbDao;
	}
	public void setFbDao(FeedbackDao fbDao) {
		this.fbDao = fbDao;
	}

	public CommentDao getCmDao() {
		return cmDao;
	}

	public void setCmDao(CommentDao cmDao) {
		this.cmDao = cmDao;
	}


}
