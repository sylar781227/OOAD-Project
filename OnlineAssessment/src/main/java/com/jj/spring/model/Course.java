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
@Table(name="COURSE")
public class Course {

	
	private int id;
	
	private String courseName;
	
	private Set<User> students;

	private Set<Exam> exams;
		
	@Id
	@Column(name="COURSE_ID")
	@GeneratedValue(strategy=GenerationType.TABLE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	 @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	    @JoinTable(
	            name="COURSE_STUDENTS",
	            joinColumns = @JoinColumn( name="COURSE_ID"),
	            inverseJoinColumns = @JoinColumn( name="STUDENT_ID")
	    )
	public Set<User> getStudents() {
		return students;
	}
	public void setStudents(Set<User> students) {
		this.students = students;
	}
	
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	    @JoinTable(
	            name="COURSE_EXAMS",
	            joinColumns = @JoinColumn( name="COURSE_ID"),
	            inverseJoinColumns = @JoinColumn( name="EXAM_ID")
	    )	
	public Set<Exam> getExams() {
		return exams;
	}
	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}
	
	@Override
	public String toString(){
		return "id="+id+", name="+courseName;
	}
}

