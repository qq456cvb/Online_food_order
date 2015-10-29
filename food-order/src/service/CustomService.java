package service;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CustomDao;
import model.Customer;
import model.User;
import iservice.IAuthorityService;

public class CustomService implements IAuthorityService{

	private CustomDao cusDao;
	
	public CustomService(){}
	
	public CustomService(CustomDao cusDao)
	{
		this.setCusDao(cusDao);
	}
	
	public boolean addUser(User user) {
		
		if (user == null)
			return false;

		//may have bugs
		//already have~?
		Customer cus = cusDao.find(user.getUserName());
		if (cus != null) {
			return false;
		}
		cusDao.add(user);
		return true;
	}

	public void deleteUser(User user) {
		if (user == null)
			return;
		
		
		cusDao.delete(user);
	}

	public void updateUser(User user) {
		if (user == null)
			return;
		

		
		cusDao.update(user);
	}


	public void setCusimg(Customer cus) {
		cusDao.setCusimg(cus);
	}
	
	public User getUserByName(String userName) {

		
		return cusDao.find(userName);
	}
	
	public Customer getUserById(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		
		return cusDao.getUserById(id);
	}


	public int validateUser(String username, String pwd) {
		Customer cus = cusDao.find(username);
		if (cus == null) {
			return 0;
		}
		if (pwd.equals(cus.getPassWord())) {
			return 3;
		}
		else {
			return 1;
		}
	}
	
	public boolean validateRegister(User user)
	{
		// can be updated
		if (cusDao.find(user.getName()) != null)
			return false;
		return true;
	}

	public CustomDao getCusDao() {
		return cusDao;
	}

	public void setCusDao(CustomDao cusDao) {
		this.cusDao = cusDao;
	}

}
