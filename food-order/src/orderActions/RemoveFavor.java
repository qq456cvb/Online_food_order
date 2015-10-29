package orderActions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Restaurant;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class RemoveFavor extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Map<String, Object> userSession;

    private WaresService waresService;
    private Long cid, rid;
    private String stat;
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest();  
    	rid = Long.parseLong(request.getParameter("rid"));
    	cid = (Long) userSession.get("cid");
    	List favorrests = (List) userSession.get("favors");
    	if (cid == null) {
    		return INPUT;
    	}
    	if (waresService.removeFavor(cid, rid)) { 
    		for (Iterator it = favorrests.iterator(); it.hasNext();) {
    			Restaurant rest = (Restaurant) it.next();
    			if (rest.getId() == rid){
    				it.remove();
    			}
    		}
    		userSession.put("favors", favorrests);
    		return SUCCESS;
    	} else {
    		stat = "removed";
    		request.setAttribute("stat", stat);
    		return ERROR;
    	}
         
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


	public Long getCid() {
		return cid;
	}


	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getRid() {
		return rid;
	}


	public void setRid(Long rid) {
		this.rid = rid;
	}



	public String getStat() {
		return stat;
	}



	public void setStat(String stat) {
		this.stat = stat;
	}


}
