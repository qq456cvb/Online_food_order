package orderActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import model.Dish;
import model.Order;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class OrderFood extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private WaresService waresService;
    private Map<String, Object> userSession;
    private Long rid, iid;
    private String auth;

     
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();  
    	setIid(Long.parseLong(request.getParameter("iid")));
    	userSession.put("iid", iid);
 //   	String auth = (String) userSession.get("auth");
    	rid = (Long) userSession.get("rid");
    	Order order = waresService.getOrderByIid(iid);
    	if (order != null) {
    		userSession.put("ordered", (String)"y");
    		List dishes = (List) (waresService.getOrderDish(order.getOrderId()));
    		userSession.put("orderdishes", dishes);
    	} else {
    		List menudishes = waresService.getMenuDish(rid);
        	userSession.put("menudishes", menudishes);
    	}
//        auth = (String) userSession.get("auth");
//        if (auth == null) {
 //       	return INPUT;
 //       }
        
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

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

}
