package com.jj.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jj.spring.model.Course;
import com.jj.spring.model.Exam;
import com.jj.spring.model.User;

public interface CourseService {

	public void addCourse(Course ques);
	public void updateCourse(Course ques);
	
	public List<Course> listCourses();
	
	public Course getCourseById(int id);
	public void removeCourse(int id);
	public void addStudentToCourse(int id, User u);
	public void addExamToCourse(int id, Exam e);
	public List<Course> listMyCourses(String userName);
	public void handleBulkUpload(MultipartFile file, int id);
	public void removeStudentFromCourse(int courseID, String userName);
	public void removeExamFromCourse(int courseID, int examID);
	
}
