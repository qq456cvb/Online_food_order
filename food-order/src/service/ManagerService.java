package service;

import java.util.List;

import dao.ManagerDao;
import iservice.IAuthorityService;
import model.Manager;
import model.Saler;
import model.User;

public class ManagerService implements IAuthorityService{

	private ManagerDao mgrDao;
	
	public ManagerService(){}
	
	public ManagerService(ManagerDao mgrDao)
	{
		this.setMgrDao(mgrDao);
	}
	

	
	public boolean addUser(User user) {
		
		if (user == null)
			return false;
		//may have bugs
		//already have~?
		Manager mgr = mgrDao.find(user.getUserName());
		if (mgr != null) {
			return false;
		}
		mgrDao.add(user);
		return true;
		
	}

	public void deleteUser(User user) {
		if (user == null)
			return;

		
		mgrDao.delete(user);
	}

	public void updateUser(User user) {
		if (user == null)
			return;
		

		
		mgrDao.update(user);
	}


	public User getUserByName(String userName) {
		if (userName == null)
			return null;
		
		return mgrDao.find(userName);
	}


	public Manager getUserById(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		
		return mgrDao.getUserById(id);
	}
	
	public int validateUser(String username, String pwd) {
		Manager mgr = mgrDao.find(username);
		if (mgr == null) {
			return 0;
		}
		if (pwd.equals(mgr.getPassWord())) {
			return 3;
		}
		else {
			return 1;
		}
	}
	
	public boolean validateRegister(User user)
	{
		// can be updated
		if (mgrDao.find(user.getName()) != null)
			return false;
		return true;
	}

	public ManagerDao getMgrDao() {
		return mgrDao;
	}

	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
	}
}
