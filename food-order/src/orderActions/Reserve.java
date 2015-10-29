package orderActions;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import model.Idle;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class Reserve extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Map<String, Object> userSession;

    private WaresService waresService;
    private CustomService cusService;
    private String resTime;
    private Idle idle;
    private Long rid;
 
    @Override
    public String execute() throws Exception {
    	String auth = (String) userSession.get("auth");
    	Long cid = (Long) userSession.get("cid");
    	rid = (Long) userSession.get("rid");
    	idle = new Idle();
    	idle.setIdleTime(resTime);
    	idle.setCus(cusService.getUserById(cid));
    	idle.setRest(waresService.getRestById(rid));
    	idle.setStatus(0);
    	boolean valid = waresService.addIdle(idle);
    	int idlesize = waresService.getCusRestIdlesSize(rid, cid);
    	int pages = idlesize / 5;
    	List idleList = new ArrayList();
    	if (pages == 0) {
    		idleList = waresService.getCusRestIdlesLimit(rid, cid, 0, idlesize);
    	} else {
    		idleList = waresService.getCusRestIdlesLimit(rid, cid, 0, 5);
    	}
    	userSession.put("idleList", idleList);
    	userSession.put("pages", pages);
    	if (valid) {
    		return SUCCESS;
    	} else {
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




	public String getResTime() {
		return resTime;
	}



	public void setResTime(String resTime) {
		this.resTime = resTime;
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



	public CustomService getCusService() {
		return cusService;
	}



	public void setCusService(CustomService cusService) {
		this.cusService = cusService;
	}


}
