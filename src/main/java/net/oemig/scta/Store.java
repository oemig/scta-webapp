package net.oemig.scta;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

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

	private Map<String, Participant> participants;
	private Map<Date,String> log;

	private Store(){
		participants=Maps.newHashMap();
		log=Maps.newHashMap();
	}
	
	public void addParticipant(Participant aParticipant){
		participants.put(aParticipant.getName(), aParticipant);
	}
	
	public Collection<Participant>getParticipants(){
		return participants.values();
	}
	
	public void log(String aSource, String aLogEntry){
		Date d=new Date();
		log.put(d, "("+aSource+"): "+aLogEntry);
	}
}
