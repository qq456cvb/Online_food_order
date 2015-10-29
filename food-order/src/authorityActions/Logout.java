package authorityActions;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Restaurant;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.ManagerService;
import service.SalerService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class Logout extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;

     
   private Map<String, Object> userSession;

    @Override
    public String execute() throws Exception {
    	
    	userSession.clear();
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




}