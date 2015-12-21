package net.oemig.scta;

import java.util.Collection;

import net.oemig.scta.model.data.User;

public class Administration {

	public Administration(){
		
	}
	
	public void addUser(User aUser){
		Store.getInstance().addUser(aUser);
	}
	
	public Collection<User> getUsers(){
		return Store.getInstance().getUsers();
	}
	
	public void log(String aSource, String aLogEntry){
		Store.getInstance().log(aSource, aLogEntry);
	}
}
