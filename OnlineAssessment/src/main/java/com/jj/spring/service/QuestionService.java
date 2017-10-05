package com.jj.spring.service;

import com.jj.spring.model.Question;

public interface QuestionService {

	public void addQuestion(Question ques);
	public void updateQuestion(Question ques);
	
	//public List<Question> listQuestions();
	
	public Question getQuestionById(int id);
	public void removeQuestion(int id);
	
}
