package model;

public class OrderDish {

	private Long odId;
	private Order order;
	private Dish dish;
	private Long num;
	
	public Long getOdId() {
		return odId;
	}
	public void setOdId(Long odId) {
		this.odId = odId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
}
