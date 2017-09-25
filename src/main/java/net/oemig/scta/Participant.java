package net.oemig.scta;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A {@link Participant} is the representation of the people taking part in a test.
 * 
 * @author christoph.oemig@oemig.net
 */
public class Participant implements Serializable {

	private static final long serialVersionUID = -5284491664481814444L;
	
	public static final String FINISHED_FREEZE_PROBE="FINISHED_FREEZE_PROBE";
	public static final String COUNTING="COUNTING";
	
	private String name;
	private String ip;
	private String id;
	private String status;
	
	public Participant() {
		setName("?");
		setIp("?");
		setId("?");
		setStatus("?");
		
	}
	
	public Participant(String anId, String anIp,String aName, String aStatus){
		id=anId;
		ip=anIp;
		name=aName;
		status=aStatus;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		this.name = aName;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
