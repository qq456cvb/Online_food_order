package authorityActions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import model.Customer;
import model.Saler;
import model.User;
import service.CustomService;
import service.ManagerService;
import service.SalerService;

import com.opensymphony.xwork2.ActionSupport;
 
public class Register extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
    
     
    private User personBean;
    private CustomService cusService;
    private ManagerService mgrService;
    private SalerService salerService;
    private int type;
    private String userName;
    private String passWord;
    private String mail;
    private String options;
     
    private Map<String, Object> userSession;
 
    @Override
    public String execute() throws Exception {
         
    	HttpServletRequest request = ServletActionContext.getRequest();
        		
    	if (options.equals("1")) {
    		Customer cus = new Customer();
    		cus.setUserName(userName);
    		cus.setPassWord(passWord);
    		cus.setEmail(mail);
    		if (cusService.addUser(cus)) {
    			request.setAttribute("username", userName);
    			request.setAttribute("password", passWord);
    			request.setAttribute("mail", mail);
    			request.setAttribute("regauth", "customer");
    			return SUCCESS;
    		} else {
    			return ERROR;
    		}
        } else if (options.equals("2")) {
        	
        	Saler saler = new Saler();
    		saler.setUserName(userName);
    		saler.setPassWord(passWord);
    		saler.setEmail(mail);
    		if (salerService.addUser(saler)) {
    			request.setAttribute("regauth", "saler");
    			request.setAttribute("username", userName);
    			request.setAttribute("password", passWord);
    			request.setAttribute("mail", mail);
    			return SUCCESS;
    		} else {
    			return ERROR;
    		}
        } else {
        	return ERROR;
        }
             
        
         
    }
     
    public User getPersonBean() {
         
        return personBean;
         
    }
     
    public void setPersonBean(User person) {
         
        this.personBean = person;
         
    }
    

	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
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

	public void setSalerService(SalerService salerService) {
		this.salerService = salerService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
 
	public void setSession(Map<String, Object> session) {
		this.setUserSession(session);
		
    }
	
	public Map<String, Object> getUserSession() {
		return userSession;
	}



	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	
	public String getOptions() {
		return options;
	}



	public void setOptions(String options) {
		this.options = options;
	}
}