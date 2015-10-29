package manageActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.SalerService;

import com.opensymphony.xwork2.ActionSupport;
 
public class ManageSaler extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private User personBean;
    private SalerService salerService;
    private Map<String, Object> userSession;
    private String sid;
    private String type;
     
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	if (type.equals("update")) {
    		Saler saler = new Saler();
    		saler.setId(Long.parseLong(sid));
 //   		saler.setAge(personBean.getAge());
 //   		saler.setName(personBean.getName());
    		saler.setPassWord(personBean.getPassWord());
    		saler.setUserName(personBean.getUserName());
    		saler.setEmail(personBean.getEmail());
    		saler.setStat(2);
    		salerService.updateUser(saler);
    		List<Saler> salers = salerService.getSaler();
    		userSession.put("salers", salers);
    		return SUCCESS; 
    		
    	} else if (type.equals("add")) {
    		
    		salerService.addUser(personBean);
    		List<Saler> salers = salerService.getSaler();
    		userSession.put("salers", salers);
    		return SUCCESS; 
    		
    	} else if (type.equals("delete")) {
    		sid = request.getParameter("sid");
    		salerService.deleteUser(salerService.getUserById(Long.parseLong(sid)));
    		List<Saler> salers = salerService.getSaler();
    		userSession.put("salers", salers);
    		return SUCCESS; 
    		
    	} else if (type.equals("yes")) {
    		
    		sid = request.getParameter("sid");
    		Saler saler = salerService.getUserById(Long.parseLong(sid));
    		saler.setStat(2);
    		salerService.updateUser(saler);
    		List<Saler> salers = salerService.getSaler();
    		userSession.put("salers", salers);
    		return SUCCESS;
    		
    	} else if (type.equals("no")) {
    		sid = request.getParameter("sid");
    		Saler saler = salerService.getUserById(Long.parseLong(sid));
    		saler.setStat(1);
    		salerService.updateUser(saler);
    		List<Saler> salers = salerService.getSaler();
    		userSession.put("salers", salers);
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






	public User getPersonBean() {
		return personBean;
	}





	public void setPersonBean(User personBean) {
		this.personBean = personBean;
	}





	public SalerService getSalerService() {
		return salerService;
	}





	public void setSalerService(SalerService salerService) {
		this.salerService = salerService;
	}









	public String getSid() {
		return sid;
	}









	public void setSid(String sid) {
		this.sid = sid;
	}

}
