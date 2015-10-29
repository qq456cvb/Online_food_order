package model;

public class Comment {
	
	private Long cmid;
	private Customer cus;
	private Restaurant rest;
	private String time;
	private String content;
	private Long pts;
	private String cmimg;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCmid() {
		return cmid;
	}
	public void setCmid(Long cmid) {
		this.cmid = cmid;
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
	public Long getPts() {
		return pts;
	}
	public void setPts(Long pts) {
		this.pts = pts;
	}
	public String getCmimg() {
		return cmimg;
	}
	public void setCmimg(String cmimg) {
		this.cmimg = cmimg;
	}

}
