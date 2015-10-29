package manageActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Restaurant;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.SalerService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class AlterRest extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private static final String UPDATE = "update";
    
    private static final String ADD = "add";
    private static final String DELETE = "delete";

	private static final String SEARCH = "search";

	
    private Restaurant restBean;
    private WaresService waresService;
    private Map<String, Object> userSession;
    private Long sid, rid;
    private String type;
    private String rname;
     
    private List rests;
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
//    	type = request.getParameter("type");
    	if (type.equals("update")) {
    		rid = (Long) userSession.get("rid");
//    		sid = (Long) userSession.get("sid");
    		Restaurant rest = waresService.getRestById(rid);
    		rest.setRestName(restBean.getRestName());
    		rest.setPlace(restBean.getPlace());
    		rest.setContact(restBean.getContact());
    		rest.setInventory(restBean.getInventory());
    		rest.setInfo(restBean.getInfo());
    		waresService.updateRest(rest);
//    		rests = waresService.getOwnRest(sid);
    		userSession.put("nowrest", rest);
    		return UPDATE; 
    		
    	} else if (type.equals("add")) {
//    		rid = (Long) userSession.get("rid");
    		sid = (Long) userSession.get("sid");
    		Restaurant rest = new Restaurant();
    		rest.setRestName(restBean.getRestName());
    		rest.setPlace(restBean.getPlace());
    		rest.setContact(restBean.getContact());
    		rest.setInventory(restBean.getInventory());
    		rest.setInfo(restBean.getInfo());
    		waresService.addRest(sid, rest);
    		List ownrests = (List) userSession.get("ownrests");
    		if (ownrests == null) {
    			ownrests = new ArrayList();
    		}
    		ownrests.add(rest);
    		userSession.put("ownrests", ownrests);
    		List rests = (List) userSession.get("rests");
    		if (rests == null) {
    			rests = new ArrayList();
    		}
    		rests.add(rest);
    		userSession.put("rests", rests);
//    		rests = waresService.getOwnRest(sid);
//   		userSession.put("nowrest", rest);
    		return ADD; 
    		
    	} else if (type.equals("delete")) {
    		rid = Long.parseLong(request.getParameter("rid"));
    		Restaurant rest = waresService.getRestById(rid);
    		sid = (Long) userSession.get("sid");
    		waresService.deleteRest(sid, rest);
    		List rests = waresService.getOwnRest(sid);
    		userSession.put("ownrests", rests);
    		rests = waresService.getAllRest();
    		userSession.put("rests", rests);
    		return DELETE; 
    	} else {
    		List<Restaurant> rests = waresService.searchRest(rname);
    		userSession.put("srests", rests);
    		return SEARCH;
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




	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}


	public Long getSid() {
		return sid;
	}




	public void setSid(Long sid) {
		this.sid = sid;
	}




	public Restaurant getRestBean() {
		return restBean;
	}



	public void setRestBean(Restaurant restBean) {
		this.restBean = restBean;
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

}
