package net.oemig.scta;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A {@link Participant} is the representation of the people taking part in a test.
 * 
 * @author christoph.oemig@oemig.net
 */
public class Participant implements Serializable {

	private static final long serialVersionUID = -5284491664481814444L;
	
	private String name;
	
	public Participant() {
		setName("?");
	}
	
	public Participant(String aName){
		name=aName;
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

}
