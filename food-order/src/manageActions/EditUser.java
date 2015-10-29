package manageActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Restaurant;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.SalerService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class EditUser extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
  
    private static final String CUSTOMER = "customer";

	private static final String SALER = "saler";
	
	private String username, password, name, email;
	private Long age;
    private CustomService cusService;
    private SalerService salerService;
    private Map<String, Object> userSession;
     
 
    @Override
    public String execute() throws Exception {
 //   	HttpServletRequest request = ServletActionContext.getRequest(); 
//    	type = request.getParameter("type");
    	String auth = (String) userSession.get("auth");
    	if (auth.equals("customer")) {
	    	Long cid = (Long) userSession.get("cid");
	    	Customer cus = new Customer();
	    	cus.setId(cid);
	    	cus.setAge(age);
	    	cus.setEmail(email);
	    	cus.setName(name);
	    	cus.setUserName(username);
	    	cus.setPassWord(password);
	    	cusService.updateUser(cus);
	    	userSession.put("cus", cus);
	    	return CUSTOMER;
    	} else if (auth.equals("saler")) {
    		Long sid = (Long) userSession.get("sid");
    		Saler saler = new Saler();
    		saler.setId(sid);
	    	saler.setAge(age);
	    	saler.setEmail(email);
	    	saler.setName(name);
	    	saler.setUserName(username);
	    	saler.setPassWord(password);
	    	salerService.updateUser(saler);
	    	userSession.put("saler", saler);
	    	return SALER;
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




	


	public void setCusService(CustomService cusService) {
		this.cusService = cusService;
	}
	
	public CustomService getCusService() {
		return this.cusService;
	}

	public void setSalerService(SalerService salerService) {
		this.salerService = salerService;
	}
	
	public SalerService getSalerService() {
		return this.salerService;
	}



	public static String getCustomer() {
		return CUSTOMER;
	}



	public static String getSaler() {
		return SALER;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getAge() {
		return age;
	}



	public void setAge(Long age) {
		this.age = age;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
 



}
