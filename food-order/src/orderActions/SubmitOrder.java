package orderActions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import model.Dish;
import model.Order;
import model.OrderDish;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class SubmitOrder extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private WaresService waresService;
    private Map<String, Object> userSession;
    private Long rid;
    private String auth;
    private List<Long> did;
    private List<Long> num;

     
 
    @Override
    public String execute() throws Exception {
 //   	HttpServletRequest request = ServletActionContext.getRequest();  
 //   	setRid(Long.parseLong(request.getParameter("rid")));
    	Long iid = (Long)userSession.get("iid");
    	Order order = new Order();
    	String time = ((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date()));
        waresService.addOrder(iid, time);
        order = waresService.getOrderByIid(iid);
        for (int i = 0; i < did.size(); i++) {
        	waresService.addOrderDish(order.getOrderId(), did.get(i), num.get(i));
        }
        return SUCCESS;
         
    }

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
 
	public void setSession(Map<String, Object> session) {
		this.setUserSession(session);
		
    }


	public WaresService getWaresService() {
		return waresService;
	}



	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}



	public Long getRid() {
		return rid;
	}



	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	

	public List<Long> getNum() {
		return num;
	}

	public void setNum(List<Long> num) {
		this.num = num;
	}

	public List<Long> getDid() {
		return did;
	}

	public void setDid(List<Long> did) {
		this.did = did;
	}


}
