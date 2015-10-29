package authorityActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Restaurant;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.ManagerService;
import service.SalerService;
import service.WaresService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
public class EnterForum extends ActionSupport implements SessionAware{
     

     
    private CustomService cusService;
     
   private Map<String, Object> userSession;

    @Override
    public String execute() throws Exception {
    	
    	if (userSession.get("auth") == null) {
    		return ERROR;
    	}
    	
    	String username = (String) userSession.get("username");
    	
    	
    	ActionContext context = ActionContext.getContext();    	
    	Map application = context.getApplication();
    	List allusers = (List) application.get("allusers");
    	if (allusers == null) {
    		allusers = new ArrayList();
    		
    	}
    	
    	if (allusers.contains((Customer)cusService.getUserByName(username))) {
    		
    	} else {
    		allusers.add((Customer)cusService.getUserByName(username));
    	}
        application.put("allusers", allusers);
        application.put("latestuser", (Customer)cusService.getUserByName(username));
        return SUCCESS;
    }
     


//	private void setIdentId(int identId) {
//    	
//    	this.identId = identId;
//    	
//    }
//    
//    public int getIdentId() {
//    	
//    	return this.identId;
//    	
//    }
    
//    public List<Ident> getIdentList() {
//        
//    	return this.identList;
//        
//    }
//    
//    public void setIdentList(List<Ident> identList) {
//            
//    	this.identList = identList;
//    }
    
 
    


	public void setSession(Map<String, Object> session) {
		this.setUserSession(session);
		
    }





	public Map<String, Object> getUserSession() {
		return userSession;
	}



	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}


	public CustomService getCusService() {
		return cusService;
	}



	public void setCusService(CustomService cusService) {
		this.cusService = cusService;
	}


	
 
}