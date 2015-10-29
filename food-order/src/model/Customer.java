package model;

import java.util.HashSet;
import java.util.Set;

public class Customer extends User
{
    private Set restaurants;
    private Set<Order> orders;
    private Set favors;
    private Set idles;
    private Set feedbacks;
    private Set comments;
    private Set vips;
    private String cusimg;
 
 

    public String getMessage()
    {
        return "Name: " + getName() + 
        " Age:      " + getAge() ;
    }



	public Set<Order> getOrders() {
		return orders;
	}



	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}




	public Set getRestaurants() {
		return restaurants;
	}



	public void setRestaurants(Set restaurants) {
		this.restaurants = restaurants;
	}



	public Set getFavors() {
		return favors;
	}



	public void setFavors(Set favors) {
		this.favors = favors;
	}



	public Set getIdles() {
		return idles;
	}



	public void setIdles(Set idles) {
		this.idles = idles;
	}



	public Set getFeedbacks() {
		return feedbacks;
	}



	public void setFeedbacks(Set feedbacks) {
		this.feedbacks = feedbacks;
	}



	public Set getComments() {
		return comments;
	}



	public void setComments(Set comments) {
		this.comments = comments;
	}



	public String getCusimg() {
		return cusimg;
	}



	public void setCusimg(String cusimg) {
		this.cusimg = cusimg;
	}



	public Set getVips() {
		return vips;
	}



	public void setVips(Set vips) {
		this.vips = vips;
	}
}
