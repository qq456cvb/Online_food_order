package authorityActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class QueryRest extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private WaresService waresService;
    

     
   private Map<String, Object> userSession;

    @Override
    public String execute() throws Exception {
    			
    	String init = (String) userSession.get("init");
    	if (init == null) {
    		List rests = new ArrayList();
    		rests = waresService.getAllRest();
    		userSession.put("rests", rests);
    		userSession.put("init", (String)"y");
    	} else {}
//        HttpServletRequest request = ServletActionContext.getRequest();
//        int init = 1;
//        request.setAttribute("init", init); 
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





	
	public WaresService getWaresService() {
		return waresService;
	}



	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}
 
}