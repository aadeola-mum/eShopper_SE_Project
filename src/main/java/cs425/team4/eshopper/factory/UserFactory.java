package cs425.team4.eshopper.factory;

import cs425.team4.eshopper.models.Role;
import cs425.team4.eshopper.models.User;

public class UserFactory {
	
	protected UserFactory(){}
    private static UserFactory userFactory;

    public static UserFactory getInstance(){
        if (userFactory==null) {
            return userFactory = new UserFactory();
        }
        return userFactory;
    }
    
    public User createUser(User user, String type) {
    	if(type.equalsIgnoreCase("buyer")) {
    		user.setRole(new Role("ROLE_BUYER"));
    		return user;
    	}
    	if(type.equalsIgnoreCase("merchant")) {
    		user.setRole(new Role("ROLE_MERCHANT"));
    		return user;
    	}
    	
    	return null;
    }
}

