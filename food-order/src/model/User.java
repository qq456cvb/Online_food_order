package model;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private Long id;
	private String name;
	private Long age;
	private String userName;
	private String passWord;
	private String email;
	
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
 
    public String getEmail()
    {
        return email;
    }
 
    public void setEmail(String email)
    {
        this.email = email;
    }
  
 
    public Long getAge()
    {
        return age;
    }
 
    public void setAge(Long age)
    {
        this.age = age;
    }
 

    public String getMessage() {
		return null;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}