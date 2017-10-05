package com.jj.spring.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jj.spring.dao.QuestionDAO;
import com.jj.spring.dao.QuestionPoolDAO;
import com.jj.spring.model.Question;
import com.jj.spring.model.QuestionPool;
import com.jj.spring.model.QuestionProp;

@Service
public class QuestionPoolServiceImpl implements QuestionPoolService {
	
	private QuestionPoolDAO questionPoolDAO;
	private QuestionDAO questionDAO;
	
	
	public void setQuestionPoolDAO(QuestionPoolDAO questionPoolDAO) {
		this.questionPoolDAO = questionPoolDAO;
	}
	
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	@Override
	@Transactional
	public List<QuestionPool> getQuestionPools()
	{
		return questionPoolDAO.getQuestionPools();
	}
	
	@Override
	@Transactional
	public QuestionPool getPoolById(int id) {
		return questionPoolDAO.getPoolById(id);
	}
	
	@Override
	@Transactional
	public void addQuestionPool(QuestionPool qp)
	{
		questionPoolDAO.addQuestionPool(qp);
	}

	@Override
	@Transactional
	public void addQuestionToPool(int poolID, QuestionProp qp) {
		QuestionPool pool = questionPoolDAO.getPoolById(poolID);
		pool.getQuestionsProp().add(qp);
		questionPoolDAO.updateQuestionPool(pool);
	}

	@Override
	@Transactional
	public QuestionProp getQuestionPropById(int qp_id) {
		return questionPoolDAO.getQuestionPropById(qp_id);
		
	}

	@Override
	@Transactional
	public void editQuestionInPool(int poolID, QuestionProp qp) {
		QuestionPool pool = questionPoolDAO.getPoolById(poolID);
		
		int id = qp.getPropID();
		Set<QuestionProp> set = pool.getQuestionsProp();
		
		QuestionProp prop = questionPoolDAO.getQuestionPropById(id);
		prop.setCourse(qp.getCourse());
		prop.setDifficulty(qp.getDifficulty());
		prop.setSection(qp.getSection());
		
		
		Question ques = questionDAO.getQuestionById(qp.getQues().getId());		
		ques.setTitle(qp.getQues().getTitle());
		ques.setDescription(qp.getQues().getDescription());
		ques.setSubmitCodeClassName(qp.getQues().getSubmitCodeClassName());
		ques.setCodetemplate(qp.getQues().getCodetemplate());
		ques.setTestcase(qp.getQues().getTestcase());
		
		prop.setQues(ques);
		
		questionPoolDAO.updateQuestionProp(prop);
		//questionDAO.updateQuestion(ques);
		
		//questionPoolDAO.updateQuestionPool(pool);
		
		
	}
}
