package net.oemig.scta;

public class EvaluationData {

	private String id;
	private int speed;
	private int correctness;
	private String questionType;
	
	public EvaluationData() {
		id="?";
		speed=0;
		correctness=0;
		questionType="?";
	}
	
	public EvaluationData(String anId,int aSpeed,int aCorrectness, String aQuestionType) {
		id=anId;
		speed=aSpeed;
		correctness=aCorrectness;
		questionType=aQuestionType;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getCorrectness() {
		return correctness;
	}
	public void setCorrectness(int correctness) {
		this.correctness = correctness;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
}
