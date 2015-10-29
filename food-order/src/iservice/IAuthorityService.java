package iservice;

import model.User;

public interface IAuthorityService {

	public boolean addUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);
	
	public Object getUserByName(String userName);
	
	public Object getUserById(Long id);
	
	public int validateUser(String username, String pwd);
	
	public boolean validateRegister(User user);
}