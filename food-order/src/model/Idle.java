package model;

public class Idle {

	private Long idleId;
	private String idleTime;
	private int status;
	private Restaurant rest;
	private Customer cus;
	private Order order;
	
	public Idle() {
		status = 0;
	}
	public Long getIdleId() {
		return idleId;
	}
	public void setIdleId(Long idleId) {
		this.idleId = idleId;
	}

	public String getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(String idleTime) {
		this.idleTime = idleTime;
	}
	public Restaurant getRest() {
		return rest;
	}
	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
