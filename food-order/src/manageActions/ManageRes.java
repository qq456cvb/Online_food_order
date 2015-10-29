package manageActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Dish;
import model.Menu;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class ManageRes extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Dish dishBean;
    private WaresService waresService;
    private Map<String, Object> userSession;
    private String iid;
    private String type;
     
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	type = request.getParameter("type");
    	iid = request.getParameter("iid");
        Long rid = (Long) userSession.get("rid");
        List idleList = (List) userSession.get("idleList");
    	if (type.equals("yes")) {
    		waresService.acceptIdle(Long.parseLong(iid));
    		idleList = waresService.getRestIdles(rid);
    		userSession.put("idleList", idleList);
    		return SUCCESS; 
    		
    	} else if (type.equals("no")) {
    		waresService.denyIdle(Long.parseLong(iid));
    		idleList = waresService.getRestIdles(rid);
    		userSession.put("idleList", idleList);
    		return SUCCESS; 
    	} else if (type.equals("finish")) {
    		waresService.finishIdle(Long.parseLong(iid));
    		idleList = waresService.getRestIdles(rid);
    		userSession.put("idleList", idleList);
    		return SUCCESS; 
    	}
             
        
        return ERROR;
         
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









	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}






	public Dish getDishBean() {
		return dishBean;
	}





	public void setDishBean(Dish dishBean) {
		this.dishBean = dishBean;
	}





	public WaresService getWaresService() {
		return waresService;
	}





	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}









	public String getIid() {
		return iid;
	}









	public void setIid(String iid) {
		this.iid = iid;
	}

}
