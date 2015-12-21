package net.oemig.scta;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

import net.oemig.scta.model.data.User;

/**
 * Singleton implementation!
 * 
 * @author christoph.oemig
 *
 */
public class Store {
	
	private static Store instance=null;
	
	public static Store getInstance(){
		if(null==instance){
			instance=new Store();
		}
		
		return instance;
	}

	private Map<String, User> users;
	private Map<Date,String> log;

	private Store(){
		users=Maps.newHashMap();
		log=Maps.newHashMap();
	}
	
	public void addUser(User aUser){
		users.put(aUser.getName(), aUser);
	}
	
	public Collection<User>getUsers(){
		return users.values();
	}
	
	public void log(String aSource, String aLogEntry){
		Date d=new Date();
		log.put(d, "("+aSource+"): "+aLogEntry);
	}
}
