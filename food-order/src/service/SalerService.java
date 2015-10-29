package service;

import java.util.List;

import dao.SalerDao;
import iservice.IAuthorityService;
import model.Saler;
import model.User;

public class SalerService implements IAuthorityService{

	private SalerDao salerDao;
	
	public SalerService(){}
	
	public SalerService(SalerDao salerDao)
	{
		this.setSalerDao(salerDao);
	}
	

	
	public List getSaler() {
		
		List<Saler> salers = salerDao.findAllSaler();
		return salers;
	}
	
	public boolean addUser(User user) {
		
		if (user == null)
			return false;
		
		//already have~?
		Saler saler = salerDao.find(user.getUserName());
		if (saler != null) {
			return false;
		}
		salerDao.add(user);
		return true;
		
	}

	public void deleteUser(User user) {
		if (user == null)
			return;
		
		
		salerDao.delete(user);
	}

	public void updateUser(User user) {
		if (user == null)
			return;
		

		
		salerDao.update(user);
	}


	public User getUserByName(String userName) {

		
		return salerDao.find(userName);
	}

	
	public Saler getUserById(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		
		return salerDao.getUserById(id);
	}

	public int validateUser(String username, String pwd) {
		Saler saler = salerDao.find(username);
		if (saler == null) {
			return 0;
		}
		if (!pwd.equals(saler.getPassWord())) {
			return 1;
		}
		else {
			if (saler.getStat() == 0) {
				return 2;
			} else {
				return 3;
			}
		}
	}
	
	public boolean validateRegister(User user)
	{
		// can be updated
		if (salerDao.find(user.getName()) != null)
			return false;
		return true;
	}

	public SalerDao getSalerDao() {
		return salerDao;
	}

	public void setSalerDao(SalerDao salerDao) {
		this.salerDao = salerDao;
	}
}
