<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String exts = "jpg,bmp,png,gif,jpeg";
	final int maxSize = 8096000;
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;   
	File file = wrapper.getFiles("myFile")[0];   
	String fileName = wrapper.getFileNames("myFile")[0];
	out.print(fileName);
	if(file.length() > maxSize){  
        String temStr= "上传文件大小超过限制。";  
        out.print("<script>alert("+temStr+")</script>");  
        return;  
    }  
    //检查扩展名  
    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
    if(!Arrays.<String>asList(exts.split(",")).contains(fileExt)){  
        String temStr= "上传文件扩展名是不允许的扩展名。\n只允许" + exts + "格式。";  
        out.print("<script>alert("+temStr+")</script>");  
        return;  
    }  

    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  

    try {    
        InputStream in = new FileInputStream(file);    
        File uploadFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)       
                .getRealPath("upload")+"/", newFileName);   
        OutputStream outps = new FileOutputStream(uploadFile);    
        byte[] buffer = new byte[1024 * 1024];    
        int length;    
        while ((length = in.read(buffer)) > 0) {    
            outps.write(buffer, 0, length);    
        }    
	
        in.close();    
        outps.close(); 
        out.print("<script>alert('上传成功')</script>"); 
        out.print("<script>parent.showImg('" + newFileName + "')</script>"); 
        session.setAttribute("src", newFileName);
    } catch (FileNotFoundException ex) {    
        ex.printStackTrace();    
    } catch (IOException ex) {    
        ex.printStackTrace();    
    }   
%>
