package idao;


public interface IUserDao {
	
	public Object find(String userName);
	
	public void add(Object o);

	void delete(Object o);

	void update(Object o);
	
	public Object getUserById(Long id);

	
}
