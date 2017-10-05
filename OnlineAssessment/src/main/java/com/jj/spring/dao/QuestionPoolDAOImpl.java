package com.jj.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jj.spring.model.QuestionPool;
import com.jj.spring.model.QuestionProp;

@Repository
public class QuestionPoolDAOImpl implements QuestionPoolDAO {
	

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<QuestionPool> getQuestionPools() 
	{
		Session session = this.sessionFactory.getCurrentSession();
		List<QuestionPool> questionPools = session.createQuery("from QuestionPool").list();
		return questionPools;
	}
	
	@Override
	public void addQuestionPool(QuestionPool qp)
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(qp);
	}
	
	@Override
	public QuestionPool getPoolById(int id) 
	{
		Session session = sessionFactory.getCurrentSession();		
		QuestionPool qp = (QuestionPool)session.load(QuestionPool.class, new Integer(id));
		return qp;
	}

	@Override
	public void updateQuestionPool(QuestionPool qp) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(qp);
	}

	@Override
	public QuestionProp getQuestionPropById(int qp_id) {
		Session session = sessionFactory.getCurrentSession();		
		QuestionProp qp = (QuestionProp)session.load(QuestionProp.class, new Integer(qp_id));
		return qp;
	}

	@Override
	public void updateQuestionProp(QuestionProp prop) {
		Session session = sessionFactory.getCurrentSession();
		session.update(prop);
	}
}
