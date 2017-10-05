package com.jj.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentQuestionID implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="STUDENT_ID")
    private String userName;
	
	@Column(name="QUESTION_ID")
    private int questionID;
	
	@Column(name="EXAM_ID")
	private int examID;
	
	public StudentQuestionID(){
		
	}
	
	public StudentQuestionID(String userName,int questionID,int examID){
		this.userName = userName;
		this.questionID = questionID;
		this.examID = examID;
	}
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}
}
