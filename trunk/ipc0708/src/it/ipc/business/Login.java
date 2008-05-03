package it.ipc.business;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {

    //public static final String MESSAGE = "Logged";

    public String execute() throws Exception {
    	if(getPassword()==null || getUsername()==null) {
    		return INPUT;
    	}
        if(getPassword().equals("21101959") && getUsername().equals("simone")) {
        	return SUCCESS;
        		//setMessage(MESSAGE);
        }
        
        return INPUT;
    }

    private String username;
    private String password;
    //private String message;

    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
//    public void setMessage(String message){
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
}
