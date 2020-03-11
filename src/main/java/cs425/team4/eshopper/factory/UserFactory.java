package cs425.team4.eshopper.factory;

import cs425.team4.eshopper.models.Merchant;
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
    
//    public User createUser(User user) {
//    	if(user.getRole() != null) {
//    		String type = user.getRole().getType(); 
//    		System.err.println("Type:  "+type);
//	    	if(type.equalsIgnoreCase("ROLE_BUYER")) {
//	    		user.setRole(new Role(1));
//	    		return user;
//	    	}
//	    	if(type.equalsIgnoreCase("ROLE_MERCHANT")) {
//	    		user.setRole(new Role(2));
//	    		return user;
//	    		//return user;
//	    	}
//	    	if(type.equalsIgnoreCase("ROLE_ADMIN")) {
//	    		user.setRole(new Role(3));
//	    		return user;
//	    	}
//    	}
//    	user.setRole(new Role(1)); ///defaults to buyers if role not supplied
//		return user;
//    }
    public User createUser(User user, String type) {
    	
    		System.err.println("Type:  "+type);
	    	if(type.equalsIgnoreCase("BUYER")) {
	    		user.setRole(new Role(1));
	    		return user;
	    	}
	    	if(type.equalsIgnoreCase("MERCHANT")) {
	    		user.setRole(new Role(2));
	    		return user;
	    		//return user;
	    	}
	    	if(type.equalsIgnoreCase("ADMIN")) {
	    		user.setRole(new Role(3));
	    		return user;
	    	}
			return null;
    	
    }
}

