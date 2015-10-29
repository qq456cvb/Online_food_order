package manageActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Idle;
import model.Restaurant;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class AlterIdle extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Idle idleBean;
    private WaresService waresService;
    private Map<String, Object> userSession;
    private Long rid, iid;
    private String type;
     
    private List rests;
 
    @Override
    public String execute() throws Exception {
         
    	if (type.equals("update")) {
//    		HttpServletRequest request = ServletActionContext.getRequest();  
//        	iid = Long.parseLong(request.getParameter("iid"));
    		rid = (Long) userSession.get("rid");
    		Idle idle = waresService.getIdleById(iid);
    		idle.setStatus(idleBean.getStatus());
    		idle.setIdleTime(idleBean.getIdleTime());
    		waresService.updateIdle(idle);
    		List idles = waresService.getRestIdles(rid);
    		userSession.put("idleList", idles);
    		return SUCCESS; 
    		
    	} else if (type.equals("add")) {
    		rid = (Long) userSession.get("rid");
 //   		waresService.addIdle(rid, idleBean);
    		List idles = waresService.getRestIdles(rid);
    		userSession.put("idleList", idles);
    		return SUCCESS; 
    		
    	} else if (type.equals("delete")) {
    		
//    		HttpServletRequest request = ServletActionContext.getRequest();  
//        	iid = Long.parseLong(request.getParameter("iid"));
    		rid = (Long) userSession.get("rid");
    		waresService.deleteIdle(waresService.getIdleById(iid));
    		List idles = waresService.getRestIdles(rid);
    		userSession.put("idleList", idles);
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



	public Idle getIdleBean() {
		return idleBean;
	}



	public void setIdleBean(Idle idleBean) {
		this.idleBean = idleBean;
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



	public Long getIid() {
		return iid;
	}



	public void setIid(Long iid) {
		this.iid = iid;
	}

}
