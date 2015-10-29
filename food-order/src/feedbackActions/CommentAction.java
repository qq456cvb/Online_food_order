package feedbackActions;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Comment;
import model.Customer;
import model.Feedback;
import model.Restaurant;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.CustomService;
import service.DataService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class CommentAction extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Map<String, Object> userSession;

    private DataService dataService;
    private WaresService waresService;
    private CustomService cusService;
    private Long cid, rid;
    private Long pts;
    private String content;
 
    public String add() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	String fname = request.getParameter("fname");
    	cid = (Long) userSession.get("cid");
    	Customer cus = new Customer();
    	cus = cusService.getUserById(cid);
    	Long rid = (Long) userSession.get("rid");
    	Restaurant rest = waresService.getRestById(rid);
    	Comment cm = new Comment();
    	cm.setCus(cus);
    	cm.setRest(rest);
    	cm.setContent(content);
    	cm.setPts(pts);
    	cm.setCmimg(fname);
    	cm.setTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date()));
    	dataService.addComment(cm);
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



	public Long getRid() {
		return rid;
	}



	public void setRid(Long rid) {
		this.rid = rid;
	}



	public WaresService getWaresService() {
		return waresService;
	}



	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}



	public Long getPts() {
		return pts;
	}



	public void setPts(Long pts) {
		this.pts = pts;
	}

}
