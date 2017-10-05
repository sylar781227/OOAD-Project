package com.jj.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION")
public class Question {
	
	@Id
	@Column(name="QUESTION_ID")
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
		
	private String title;
	
	private String description;
	
	private String codetemplate;
	
	private String submitCodeClassName;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=100000)	
	private String testcase;	
	
	private int score;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodetemplate() {
		return codetemplate;
	}

	public void setCodetemplate(String codetemplate) {
		this.codetemplate = codetemplate;
	}

	public String getTestcase() {
		return testcase;
	}

	public void setTestcase(String testcase) {
		this.testcase = testcase;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		return "id="+id+", title="+title;
	}

	public String getSubmitCodeClassName() {
		return submitCodeClassName;
	}

	public void setSubmitCodeClassName(String submitCodeClassName) {
		this.submitCodeClassName = submitCodeClassName;
	}
	
}
