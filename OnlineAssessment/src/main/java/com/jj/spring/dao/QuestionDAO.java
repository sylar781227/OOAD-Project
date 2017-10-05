package com.jj.spring.dao;

import com.jj.spring.model.Question;

public interface QuestionDAO {

	public void addQuestion(Question p);
	public void updateQuestion(Question p);
	
	//public List<Question> listQuestions();
	
	public Question getQuestionById(int id);
	public void removeQuestion(int id);
}
