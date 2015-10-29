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
 
public class ManageVip extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Dish dishBean;
    private WaresService waresService;
    private Map<String, Object> userSession;
    private String vid;
    private String type;
     
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	type = request.getParameter("type");
    	vid = request.getParameter("vid");
        Long rid = (Long) userSession.get("rid");
        Long cid = (Long) userSession.get("cid");
        List vipList = (List) userSession.get("vipList");
    	if (type.equals("yes")) {
    		waresService.addVip(Long.parseLong(vid));
    		vipList = waresService.getRestVips(rid);
    		userSession.put("vipList", vipList);
    		return SUCCESS; 
    		
    	} else if (type.equals("no")) {
    		waresService.removeVip(Long.parseLong(vid));
    		vipList = waresService.getRestVips(rid);
    		userSession.put("vipList", vipList);
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









	public String getVid() {
		return vid;
	}









	public void setVid(String vid) {
		this.vid = vid;
	}


}
