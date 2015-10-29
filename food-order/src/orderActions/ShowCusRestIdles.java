package orderActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Idle;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class ShowCusRestIdles extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Map<String, Object> userSession;

    private WaresService waresService;
    private Idle idle;
    private Long rid;
 
    @Override
    public String execute() throws Exception {
    	Long cid = (Long) userSession.get("cid");
    	HttpServletRequest request = ServletActionContext.getRequest();  
    	setRid(Long.parseLong(request.getParameter("rid")));
    	List idleList = waresService.getCusRestIdles(rid, cid);
    	userSession.put("idleList", idleList);
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






	public Idle getIdle() {
		return idle;
	}



	public void setIdle(Idle idle) {
		this.idle = idle;
	}



	public Long getRid() {
		return rid;
	}



	public void setRid(Long rid) {
		this.rid = rid;
	}




}
