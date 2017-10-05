package com.jj.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jj.spring.model.Result;

@Repository
public class ResultDAOImpl implements ResultDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addResult(Result p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Result saved successfully, Result Details="+p);
	}

	@Override
	public void updateResult(Result p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Result updated successfully, Result Details="+p);
	}
	
	@Override
	public void saveupdateResult(Result p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
		logger.info("Result updated successfully, Result Details="+p);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> listResults() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Result> ResultsList = session.createQuery("from Result").list();
		for(Result p : ResultsList){
			logger.info("Result List::"+p);
		}
		return ResultsList;
	}

	@Override
	public Result getResultById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Result p = (Result) session.load(Result.class, new Integer(id));
		logger.info("Result loaded successfully, Result details="+p);
		return p;
	}

	@Override
	public void removeResult(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Result p = (Result) session.load(Result.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Result deleted successfully, Result details="+p);
	}

	@Override
	public boolean hasExamStarted(String username, int exam_id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Result> results = session.createQuery("select r from Result as r where r.id.userName = '"+username+"' AND r.id.examID = "+exam_id).list();
		
		if(results==null || results.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public List<Result> getResults(String username, int exam_id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Result> results = session.createQuery("select r from Result as r where r.id.userName = '"+username+"' AND r.id.examID = "+exam_id).list();
		
		return results;
	}

	@Override
	public List<Result> getResultsForExam(int examID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Result> results = session.createQuery("select r from Result as r where r.id.examID = "+examID+" order by r.id.userName").list();
		
		return results;
	}

}
