package com.jj.spring.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Result")
public class Result {
	
	@EmbeddedId
	private StudentQuestionID id;

	private String submitedCode;
	
	private String resultMessage;
	
	private int score;

	public String getSubmitedCode() {
		return submitedCode;
	}

	public void setSubmitedCode(String submitedCode) {
		this.submitedCode = submitedCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public StudentQuestionID getId() {
		return id;
	}

	public void setId(StudentQuestionID id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
