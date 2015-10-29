package model;

import java.util.Set;

public class Saler extends User{

	private Set<Restaurant> rests;
	
	private Integer stat;
	public String getMessage()
    {
        return "Name: " + getName() + 
        " Age:      " + getAge() ;
    }

	public Set<Restaurant> getRests() {
		return rests;
	}

	public void setRests(Set<Restaurant> rests) {
		this.rests = rests;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
}
