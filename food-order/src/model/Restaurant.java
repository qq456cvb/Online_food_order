package model;

import java.util.Set;

public class Restaurant {
	
	private Long id;
	private Saler owner;
	private String restName;
	private Menu menu;
	private Set<Order> orders;
	private String place;
	private Long inventory;
	private String info;
	private String contact;
	private Set favors;
	private Set idles;
	private Set comments;
	private String restimg;
	private Set vips;
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Long getInventory() {
		return inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public Saler getOwner() {
		return owner;
	}

	public void setOwner(Saler owner) {
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set getComments() {
		return comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public String getRestimg() {
		return restimg;
	}

	public void setRestimg(String restimg) {
		this.restimg = restimg;
	}

	public Set getVips() {
		return vips;
	}

	public void setVips(Set vips) {
		this.vips = vips;
	}

}
