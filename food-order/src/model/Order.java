package model;

import java.util.Set;

public class Order {
	
	private Long orderId;
	private Idle idle;
	private Set orderDish;
	private String orderTime;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Idle getIdle() {
		return idle;
	}
	public void setIdle(Idle idle) {
		this.idle = idle;
	}
	
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Set getOrderDish() {
		return orderDish;
	}
	public void setOrderDish(Set orderDish) {
		this.orderDish = orderDish;
	}

}
	
	