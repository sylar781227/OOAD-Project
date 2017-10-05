package com.jj.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jj.spring.dao.QuestionDAO;
import com.jj.spring.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionDAO questionDAO;

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	@Override
	@Transactional
	public void addQuestion(Question ques) {
		this.questionDAO.addQuestion(ques);
	}

	@Override
	@Transactional
	public void updateQuestion(Question ques) {
		this.questionDAO.updateQuestion(ques);
	}

	@Override
	@Transactional
	public Question getQuestionById(int id) {
		return this.questionDAO.getQuestionById(id);
	}

	@Override
	@Transactional
	public void removeQuestion(int id) {
		this.questionDAO.removeQuestion(id);
	}

}
