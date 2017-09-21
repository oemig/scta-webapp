package net.oemig.scta;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ResponseData {
	
	private String id;
	private String participantId;
	private boolean correct;
	private int milliseconds;
	private String questionType;
	
	
	public ResponseData() {
		id="?";
		participantId="?";
		correct=false;
		milliseconds=0;
		questionType="?";
	}
	
	public ResponseData(String anId, String aParticipantId, boolean aCorrect, int someMilliseconds, String aQuestionType){
		id=anId;
		participantId=aParticipantId;
		correct=aCorrect;
		milliseconds=someMilliseconds;
		questionType=aQuestionType;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public int getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
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

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	

}
