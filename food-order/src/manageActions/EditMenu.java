package manageActions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.Dish;
import model.Menu;
import model.Saler;
import model.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.WaresService;
import service.WaresService;

import com.opensymphony.xwork2.ActionSupport;
 
public class EditMenu extends ActionSupport implements SessionAware{
     
    private static final long serialVersionUID = 1L;
     
    private Dish dishBean;
    private WaresService waresService;
    private Map<String, Object> userSession;
    private String did;
    private String type;
     
 
    @Override
    public String execute() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
        Long rid = (Long) userSession.get("rid");
    	if (type.equals("update")) {
    		Dish dish = new Dish();
    		dish.setDishId(Long.parseLong(did));
 //   		saler.setAge(dishBean.getAge());
 //   		saler.setName(dishBean.getName());
    		dish.setDishName(dishBean.getDishName());
    		dish.setPrice(dishBean.getPrice());
    		dish.setInfo(dishBean.getInfo());
    		waresService.updateDish(dish);
    		List<Dish> dishes = waresService.getMenuDish(rid);
    		userSession.put("menudishes", dishes);
    		return SUCCESS; 
    		
    	} else if (type.equals("add")) {
    		List<Dish> dishes = waresService.getMenuDish(rid);
    		if (dishes.size() == 0 || dishes == null) {
    			Menu menu = new Menu();
    			waresService.addMenu(rid, menu);
    		}
    		dishBean.setMenu(waresService.getMenuByRid(rid));
    		waresService.addDish(dishBean);
    		dishes = waresService.getMenuDish(rid);
    		
    		userSession.put("menudishes", dishes);
    		return SUCCESS; 
    		
    	} else if (type.equals("delete")) {
    		did = request.getParameter("did");
    		waresService.deleteDish(waresService.getDishById(Long.parseLong(did)));
    		List<Dish> dishes = waresService.getMenuDish(rid);
    		userSession.put("menudishes", dishes);
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






	public Dish getDishBean() {
		return dishBean;
	}





	public void setDishBean(Dish dishBean) {
		this.dishBean = dishBean;
	}





	public WaresService getWaresService() {
		return waresService;
	}





	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}









	public String getDid() {
		return did;
	}









	public void setDid(String did) {
		this.did = did;
	}

}
