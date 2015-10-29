package orderActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Comment;
import model.Dish;
import model.Restaurant;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.DataService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class EnterRest extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private WaresService waresService;
    private DataService dataService;
    private Map<String, Object> userSession;
    private Long rid;
    private String auth;

     
 
    @Override
    public String execute() throws Exception {
         
    	HttpServletRequest request = ServletActionContext.getRequest();  
    	setRid(Long.parseLong(request.getParameter("rid")));
    	auth = (String) userSession.get("auth");
    	List ownrests = (List) userSession.get("ownrests");
    	userSession.put("idleList", waresService.getRestIdles(rid));
    	userSession.put("rid", rid);
    	userSession.put("nowrest", waresService.getRestById(rid));
    	userSession.put("restname", waresService.getRestById(rid).getRestName());
    	List<Dish> dishes = waresService.getMenuDish(rid);
		userSession.put("menudishes", dishes);
		List restcms = dataService.getCmByRidLimit(rid, 0, 5);
		List restcmsall = dataService.getCmByRid(rid);
		userSession.put("restcms", restcms);
		List vipList = waresService.getRestVips(rid);
		userSession.put("vipList", vipList);
		Long cid = (Long) userSession.get("cid");
		if (cid != null) {
			if(waresService.isVip(cid, rid)) {
				userSession.put("vip", 1);
			} else {
				userSession.put("vip", 0);
			}
		}
    	
    	if (auth != null) {
	    	if (auth.equals("saler")) {
	    		for (int i = 0; i < ownrests.size(); i++) {
	    			if (((Restaurant) ownrests.get(i)).getId() == rid) {
	    				request.setAttribute("owned", "y");
	    			}
	    		}
	    	}
    	}
    	
    	Long totalpts = (long) 0;
    	for (int i = 0; i < restcmsall.size(); i++) {
    		totalpts += ((Comment)restcmsall.get(i)).getPts();
    	}
    	double restpt;
    	if (restcmsall.size() == 0) {
    		restpt = 0;
    	} else {
    		restpt = (double) totalpts / restcmsall.size();
    	}
        userSession.put("restpt", restpt);
        userSession.put("restcmssize", restcmsall.size());
        int restcmpages = restcmsall.size() / 5;
        userSession.put("restcmpages", restcmpages);
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


	public DataService getDataService() {
		return dataService;
	}
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public Long getRid() {
		return rid;
	}



	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

}
