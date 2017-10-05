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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION_POOL")
public class QuestionPool {
	
	private int id;
	private String title;
	private Set<QuestionProp> questionsProp;
		
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(
            name="QUESTION_POOL_PROPS",
            joinColumns = @JoinColumn( name="QUESTION_POOL_ID"),
            inverseJoinColumns = @JoinColumn(name="QPROP_ID")
    )
	public Set<QuestionProp> getQuestionsProp() {
		return questionsProp;
	}
	public void setQuestionsProp(Set<QuestionProp> questionsProp) {
		this.questionsProp = questionsProp;
	}
	
	@Id
	@Column(name="QUESTION_POOL_ID")
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
	
	@Override
	public String toString(){
		return "id="+id+", title="+title;
	}
}
