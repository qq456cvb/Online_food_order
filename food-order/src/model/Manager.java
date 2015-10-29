package model;

import java.util.Set;

public class Manager extends User{
	
	private Set cusFollowed;
	public String getMessage()
    {
        return "Name: " + getName() + 
        " Age:      " + getAge() ;
    }
	public Set getCusFollowed() {
		return cusFollowed;
	}
	public void setCusFollowed(Set cusFollowed) {
		this.cusFollowed = cusFollowed;
	}
}
