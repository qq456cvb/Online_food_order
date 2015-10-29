package model;

import java.util.Set;

public class Menu {
	private Long menuId;
	private Set dishes;
	private Restaurant rest;

	public Set getDishes() {
		return dishes;
	}

	public void setDishes(Set dishes) {
		this.dishes = dishes;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	
}
