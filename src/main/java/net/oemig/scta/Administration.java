package net.oemig.scta;

import java.util.Collection;

public class Administration {

	public Administration(){
		
	}
	
	public void addParticipant(Participant aParticipant){
		Store.getInstance().addParticipant(aParticipant);
	}
	
	public Collection<Participant> getParticipants(){
		return Store.getInstance().getParticipants();
	}
	
	public void log(String aSource, String aLogEntry){
		Store.getInstance().log(aSource, aLogEntry);
	}
}
