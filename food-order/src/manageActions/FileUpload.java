package manageActions;  
  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  

import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;  
  

import org.apache.struts2.interceptor.SessionAware;

import service.*;
import model.*;

import com.opensymphony.xwork2.ActionSupport;  
  
public class FileUpload extends ActionSupport implements SessionAware{  
    private static final long serialVersionUID = 572146812454l;  
    private static final int BUFFER_SIZE = 16 * 1024;  
    private List<File> myFile = new ArrayList<File>();    
    private List<String> contentType = new ArrayList<String>();  
    private List<String> fileName = new ArrayList<String>();    //文件名  
    private List<String> imageFileName = new ArrayList<String>();  
    private String caption;  
    private String type;
    private Long did;
    private WaresService waresService;
    private CustomService cusService;
    
    private static final String CUSTOMER = "customer";
    private static final String REST = "rest";
    private static final String MENU = "menu";
    
    private Map<String, Object> userSession;
  
    private static void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    }  
  
    @Override  
    public String execute() {  
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
        if (myFile == null)  
            return INPUT;  
        for (int i = 0; i < myFile.size(); i++) {  
        	if (type.equals("rest")) {       		 
        	    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);
        		imageFileName.add(newFileName + getExtention(this.getMyFileFileName().get(i)));
        		Restaurant rest = (Restaurant) userSession.get("nowrest");
        		rest.setRestimg(imageFileName.get(i));
        		waresService.updateRest(rest);
        		userSession.put("nowrest", rest);
        	} else if (type.equals("cus")) {
        		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);
        		imageFileName.add(newFileName + getExtention(this.getMyFileFileName().get(i)));
        		Customer cus = (Customer) userSession.get("cus");
        		cus.setCusimg(imageFileName.get(i));
        		cusService.setCusimg(cus);
        		userSession.put("cus", cus);
        	} else if (type.equals("menu")) {
        		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000);
        		imageFileName.add(newFileName + getExtention(this.getMyFileFileName().get(i)));
        		Dish dish = waresService.getDishById(did);
        		dish.setDishimg(imageFileName.get(i));
        		waresService.updateDish(dish);
        		List<Dish> menudishes = (List) userSession.get("menudishes");
        		for (int j = 0; j < menudishes.size(); j++) {
        			if (((Dish)menudishes.get(j)).getDishId() == did) {
        				menudishes.remove(j);
        				menudishes.add(j, dish);
        			}
        		}
        		userSession.put("menudishes", menudishes);
        	}
//            userSession.put("img", imageFileName.get(i));
            if (type.equals("rest")) {
	            File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)       
	                    .getRealPath("restLogos")
	                    + "/" + imageFileName.get(i)); 
	            copy(myFile.get(i), imageFile);
	            return REST;
            } else if (type.equals("cus")) {
            	File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)       
	                    .getRealPath("cusLogos")
	                    + "/" + imageFileName.get(i)); 
            	copy(myFile.get(i), imageFile);
            	return CUSTOMER;
            } else if (type.equals("menu")) {
            	File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)       
	                    .getRealPath("restdishes")
	                    + "/" + imageFileName.get(i)); 
            	copy(myFile.get(i), imageFile);
            	return MENU;
            }
              //把图片写入到上面设置的路径里  
            
        }  
          
        return ERROR;
        
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

	
    public List<File> getMyFile() {  
        return myFile;  
    }  
  
    public void setMyFile(List<File> myFile) {  
        this.myFile = myFile;  
    }  
  
    public List<String> getContentType() {  
        return contentType;  
    }  
  
    public void setContentType(List<String> contentType) {  
        this.contentType = contentType;  
    }  
  
  
    public List<String> getMyFileFileName() {  
        return fileName;  
    }  
  
    public void setMyFileFileName(List<String> fileName) {  
        this.fileName = fileName;  
    }  
  
  
    public List<String> getImageFileName() {  
        return imageFileName;  
    }  
  
    public void setImageFileName(List<String> imageFileName) {  
        this.imageFileName = imageFileName;  
    }  
  
    public String getCaption() {  
        return caption;  
    }  
  
    public void setCaption(String caption) {  
        this.caption = caption;  
    }  
  
    public static int getBufferSize() {  
        return BUFFER_SIZE;  
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public WaresService getWaresService() {
		return waresService;
	}

	public void setWaresService(WaresService waresService) {
		this.waresService = waresService;
	}

	public CustomService getCusService() {
		return cusService;
	}

	public void setCusService(CustomService cusService) {
		this.cusService = cusService;
	}  
  
}  