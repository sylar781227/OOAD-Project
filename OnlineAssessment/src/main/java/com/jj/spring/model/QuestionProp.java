package com.jj.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION_PROP")
public class QuestionProp {
	
	@Id
	@Column(name="QPROP_ID")
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int propID;
	
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinColumn(name = "QUESTION_ID")
    private Question ques;
	
	private int difficulty;
	
	private String course;
	
	private String section;

	public Question getQues() {
		return ques;
	}

	public void setQues(Question ques) {
		this.ques = ques;
	}

	public int getPropID() {
		return propID;
	}

	public void setPropID(int propID) {
		this.propID = propID;
	}
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
}
