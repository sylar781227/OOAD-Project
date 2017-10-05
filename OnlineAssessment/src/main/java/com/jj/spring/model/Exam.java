package com.jj.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="EXAM")
public class Exam {
	
	private int id;
	
	private String title;
	
	private int numQuestions;
	
	private String timeLimit;

	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	private String password;
	
	private Set<Question> questions;
	
	@Id
	@Column(name="EXAM_ID")
	@GeneratedValue(strategy=GenerationType.TABLE)
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

	public int getNumQuestions() {
		return numQuestions;
	}
	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(
            name="EXAM_QUESTIONS",
            joinColumns = @JoinColumn( name="EXAM_ID"),
            inverseJoinColumns = @JoinColumn( name="QUESTION_ID")
    )
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	@Override
	public String toString(){
		return "id="+id+", title="+title;
	}
	
}
