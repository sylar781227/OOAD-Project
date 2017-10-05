package com.jj.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jj.spring.dao.ExamDAO;
import com.jj.spring.dao.QuestionDAO;
import com.jj.spring.dao.ResultDAO;
import com.jj.spring.model.DisplayResult;
import com.jj.spring.model.Exam;
import com.jj.spring.model.Question;
import com.jj.spring.model.Result;

@Service
public class ExamServiceImpl implements ExamService {
	
	private ExamDAO examDAO;
	private ResultDAO resultDAO;
	private QuestionDAO questionDAO;

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
	
	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}

	@Override
	@Transactional
	public void addExam(Exam p) {
		this.examDAO.addExam(p);
	}

	@Override
	@Transactional
	public void updateExam(Exam p) {
		this.examDAO.updateExam(p);
	}

	@Override
	@Transactional
	public List<Exam> listExams() {
		return this.examDAO.listExams();
	}

	@Override
	@Transactional
	public Exam getExamById(int id) {
		return this.examDAO.getExamById(id);
	}

	@Override
	@Transactional
	public void removeExam(int id) {
		this.examDAO.removeExam(id);
	}

	@Override
	@Transactional
	public List<Question> listQuestions(int course_id, int exam_id) {
		
		
		return examDAO.listQuestions(exam_id, course_id);
	}

	@Override
	@Transactional
	public void addQuestionToExam(int exam_id, Question ques) {
		Exam ex = this.getExamById(exam_id);
		ex.getQuestions().add(ques);
		this.updateExam(ex);		
	}

	@Override
	@Transactional
	public boolean hasExamStarted(String username, int exam_id) {
		return resultDAO.hasExamStarted(username,exam_id);
	}

	@Override
	@Transactional
	public List<DisplayResult> getResults(String username, int exam_id) {
		List<Result> results = resultDAO.getResults(username,exam_id);
		List<DisplayResult> dResults = new ArrayList<DisplayResult>();
		
		for(Result r:results){
			Question q = questionDAO.getQuestionById(r.getId().getQuestionID());
			dResults.add(new DisplayResult(q,r));
		}
		
		return dResults;
	}

	@Override
	@Transactional
	public void addQuestionToExamByID(int exam_id, int q_id) {
		Exam ex = this.getExamById(exam_id);
		Question q = questionDAO.getQuestionById(q_id);		
		ex.getQuestions().add(q);
		this.examDAO.updateExam(ex);
	}

	@Override
	@Transactional
	public void removeQuestionFromExam(int exam_id, int ques_id) {
		Exam exam = examDAO.getExamById(exam_id);
		Question q = questionDAO.getQuestionById(ques_id);
		exam.getQuestions().remove(q);
		updateExam(exam);
	}

}
