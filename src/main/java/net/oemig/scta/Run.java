package net.oemig.scta;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Run {
	
	private String id;
	private int freezeProbes;
	private String description;
	private String title;
	private String status;
	
	public Run() {
		id="UNKNOWN";
		freezeProbes=0;
		description="No description";
		title="Untitled";
		status="UNKNOWN";
	}
	public Run(String anId, int someFreezeProbes,String aDescription, String aTitel, String aStatus){
		id=anId;
		freezeProbes=someFreezeProbes;
		description=aDescription;
		title=aTitel;
		status=aStatus;
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

	public int getFreezeProbes() {
		return freezeProbes;
	}

	public void setFreezeProbes(int freezeProbes) {
		this.freezeProbes = freezeProbes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
