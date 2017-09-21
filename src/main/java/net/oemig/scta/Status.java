package net.oemig.scta;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Status {
	
	public static final String CLOSED="CLOSED";
	public static final String OPEN="OPEN";
	public static final String RUNNING="RUNNING";
	public static final String PROBING="PROBING";
	public static final String COMPLETED="COMPLETED";
	
	private String name;
	
	public Status() {
		setName("UNKNOWN");
	}
	
	public Status(String aName){
		setName(aName);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
