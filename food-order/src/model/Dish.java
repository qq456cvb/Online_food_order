package model;

import java.util.Set;

public class Dish {
	
	private Long dishId;
	private Set orderDish;
	private String dishName;
	private double price;
	private Long stat;
	private String info;
	private Menu menu;
	private String dishimg;
	
	public Long getDishId() {
		return dishId;
	}
	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getStat() {
		return stat;
	}
	public void setStat(Long stat) {
		this.stat = stat;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Set getOrderDish() {
		return orderDish;
	}
	public void setOrderDish(Set orderDish) {
		this.orderDish = orderDish;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDishimg() {
		return dishimg;
	}
	public void setDishimg(String dishimg) {
		this.dishimg = dishimg;
	} 
	
}
