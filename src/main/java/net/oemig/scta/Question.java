package net.oemig.scta;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Question {
	
	public static final String GROUP_WHO="GROUP_WHO";
	public static final String GROUP_WHAT="GROUP_WHAT";
	public static final String SELF_WHO="SELF_WHO";
	public static final String SELF_WHAT="SELF_WHAT";
	public static final String COORDINATION_WHO="COORDINATION_WHO";
	public static final String COORDINATION_WHAT="COORDINATION_WHAT";
	
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String correctAnswer;
	private String questionType;
	
	public Question(){
		question="?";
		answer1="?";
		answer2="?";
		answer3="?";
		correctAnswer="?";
		questionType="?";
	}
	
	public Question(
			String aQuestion,
			String anAnswer1,
			String anAnswer2,
			String anAnswer3,
			String aCorrectAnswer,
			String aQuestionType
			){
		question=aQuestion;
		answer1=anAnswer1;
		answer2=anAnswer2;
		answer3=anAnswer3;
		correctAnswer=aCorrectAnswer;
		questionType=aQuestionType;
		
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

}
