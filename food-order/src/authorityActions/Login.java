package authorityActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Feedback;
import model.Restaurant;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.DataService;
import service.ManagerService;
import service.SalerService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class Login extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;

	private static final String CUSTOMER = "customer";

	private static final String SALER = "saler";

	private static final String MANAGER = "manager";
     
    private int type;
    private String userName;
    private String passWord;
    private String options;
    private String stat;
    private CustomService cusService;
    private ManagerService mgrService;
    private SalerService salerService;
    private WaresService waresService;
    private DataService dataService;
    
    List<Saler> salers;
    List<Restaurant> rests;
     
   private Map<String, Object> userSession;

    @Override
    public String execute() throws Exception {
    	
    	if (userSession.get("username") != null) {
    		return ERROR;
    	}
        
        if (options.equals("1")) {
        	if (cusService.validateUser(userName, passWord) != 3)
        	{ 
//        		HttpServletRequest request = ServletActionContext.getRequest();  
        		stat = "fail";
//        		request.setAttribute("stat", stat);
        		return INPUT;
        	} else {
        		String auth = "customer";
        		
        		rests = waresService.getAllRest();
        		userSession.put("rests", rests);
        		rests = waresService.getFavorRest(cusService.getUserByName(userName).getId());
        		userSession.put("favors", rests);
        		Customer cus = (Customer) cusService.getUserByName(userName);
        		userSession.put("cid", cus.getId());
        		List cusIdles = new ArrayList();
        		cusIdles = waresService.getCusIdles(cus.getId());
        		userSession.put("cusIdles", cusIdles);
        		userSession.put("username", userName);
                userSession.put("auth", auth);
                userSession.put("cus", cus);
                
        		return CUSTOMER;
        	}
        } else if (options.equals("2")) {
        	if (salerService.validateUser(userName, passWord) != 3)
        	{ 
//        		HttpServletRequest request = ServletActionContext.getRequest();  
        		stat = "fail";
//        		request.setAttribute("stat", stat);
        		return INPUT;
        	} else {
        		String auth = "saler";
        		rests = waresService.getOwnRest(salerService.getUserByName(userName).getId());
        		userSession.put("ownrests", rests);
        		Saler saler = (Saler) salerService.getUserByName(userName);
        		userSession.put("sid", salerService.getUserByName(userName).getId());
        		userSession.put("username", userName);
                userSession.put("auth", auth);
                userSession.put("saler", saler);
        		return SALER;
        	}
        } else {
        	if (mgrService.validateUser(userName, passWord) != 3)
        	{ 
//        		HttpServletRequest request = ServletActionContext.getRequest();  
        		stat = "fail";
//        		request.setAttribute("stat", stat);
        		return INPUT;
        	} else {
        		String auth = "manager";
        		List feedbacks = dataService.getAllFb();
        		List rests = waresService.getAllRest();
        		salers = salerService.getSaler();
                userSession.put("salers", salers);        		
        		userSession.put("username", userName);
        		userSession.put("auth", auth);
        		userSession.put("fbs", feedbacks);
        		userSession.put("rests", rests);
        		return MANAGER;
        	}
        }
        
        
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



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}

	public String getStat() {
		return stat;
	}



	public void setStat(String stat) {
		this.stat = stat;
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



	public ManagerService getMgrService() {
		return mgrService;
	}



	public void setMgrService(ManagerService mgrService) {
		this.mgrService = mgrService;
	}



	public SalerService getSalerService() {
		return salerService;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getPassWord() {
		return this.passWord;
	}

	public void setSalerService(SalerService salerService) {
		this.salerService = salerService;
	}
	
	public WaresService getWaresService() {
		return waresService;
	}

	

	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}



	public String getOptions() {
		return options;
	}



	public void setOptions(String options) {
		this.options = options;
	}



	public DataService getDataService() {
		return dataService;
	}



	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
 
}