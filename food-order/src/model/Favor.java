package model;

public class Favor {

	private Long favorId;
	private Customer cus;
	private Restaurant rest;
	private int stat;
	
	public Long getFavorId() {
		return favorId;
	}
	public void setFavorId(Long favorId) {
		this.favorId = favorId;
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public Restaurant getRest() {
		return rest;
	}
	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	
}
