package org.oa.ajax_rest_demo.model;

public enum UserRoleType {
    USER("ROLE_USER"),
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN");
	     
	    String userRoleType;
	     
	    private UserRoleType(String userProfileType){
	        this.userRoleType = userProfileType;
	    }
	     
	    public String getUserRoleType(){
	        return userRoleType;
	    }
}
