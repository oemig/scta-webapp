package net.oemig.scta;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CountData {
	
	private String id;
	private String participantId;
	private String letter;
	private int quantity;
	
	public CountData() {
		id="?";
		participantId="?";
		letter="?";
		quantity=0;
	}
	
	public CountData(String anId,String aParticipantId, String aLetter, int aQuantity){
		id=anId;
		participantId=aParticipantId;
		letter=aLetter;
		quantity=aQuantity;
	}
	
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String aParticipantId) {
		this.participantId = aParticipantId;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this,obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
