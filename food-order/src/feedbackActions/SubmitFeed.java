package feedbackActions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Feedback;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.DataService;

import com.opensymphony.xwork2.ActionSupport;
 
public class SubmitFeed extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Map<String, Object> userSession;

    private DataService dataService;
    private CustomService cusService;
    private Long cid;
    private String content;
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	request.setAttribute("tipMessage", "提交成功！");
    	cid = (Long) userSession.get("cid");
    	Customer cus = new Customer();
    	cus = cusService.getUserById(cid);
    	Feedback fb = new Feedback();
    	fb.setCus(cus);
    	fb.setContent(content);
    	fb.setTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date()));
    	dataService.addFeedback(fb);
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





	public Long getCid() {
		return cid;
	}


	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DataService getDataService() {
		return dataService;
	}



	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public CustomService getCusService() {
		return cusService;
	}



	public void setCusService(CustomService cusService) {
		this.cusService = cusService;
	}

}
