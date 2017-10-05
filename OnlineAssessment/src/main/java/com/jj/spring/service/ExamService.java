package com.jj.spring.service;

import java.util.List;

import com.jj.spring.model.DisplayResult;
import com.jj.spring.model.Exam;
import com.jj.spring.model.Question;

public interface ExamService {

	public void addExam(Exam p);
	public void updateExam(Exam p);
	public List<Exam> listExams();
	public Exam getExamById(int id);
	public void removeExam(int id);
	public List<Question> listQuestions(int exam_id, int course_id);
	public void addQuestionToExam(int exam_id, Question ques);
	public boolean hasExamStarted(String username, int exam_id);
	public List<DisplayResult> getResults(String username, int exam_id);
	public void addQuestionToExamByID(int exam_id, int q_id);
	public void removeQuestionFromExam(int exam_id, int ques_id);
 	
}
