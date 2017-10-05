package com.jj.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jj.spring.model.Exam;
import com.jj.spring.model.Question;

@Repository
public class ExamDAOImpl implements ExamDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addExam(Exam p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Exam saved successfully, Exam Details="+p);
	}

	@Override
	public void updateExam(Exam p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Exam updated successfully, Exam Details="+p);
	}
	
	@Override
	public void mergeExam(Exam p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(p);
		logger.info("Exam merged successfully, Exam Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> listExams() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Exam> examsList = session.createQuery("from Exam").list();
		for(Exam p : examsList){
			logger.info("Exam List::"+p);
		}
		return examsList;
	}

	@Override
	public Exam getExamById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Exam p = (Exam) session.load(Exam.class, new Integer(id));
		logger.info("Exam loaded successfully, Exam details="+p);
		return p;
	}

	@Override
	public void removeExam(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Exam p = (Exam) session.load(Exam.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Exam deleted successfully, exam details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> listQuestions(int exam_id, int course_id) {
		Session session = this.sessionFactory.getCurrentSession();
		                                                   //select ques from Question ques join fetch role.rights    
		List<Question> quesionsList = session.createQuery("select exam.questions from Exam as exam where exam.id = "+exam_id).list();
	
		for(Question q : quesionsList){
			logger.info("Questions List::"+q);
		}
		return quesionsList;
	}

	/*@Override
	public void addQuestionToExam(int exam_id, Question ques) {
		
	}*/

}
