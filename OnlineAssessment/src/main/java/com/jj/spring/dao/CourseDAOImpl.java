package com.jj.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jj.spring.model.Course;

@Repository
public class CourseDAOImpl implements CourseDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCourse(Course p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Course saved successfully, Course Details="+p);
	}

	@Override
	public void updateCourse(Course p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Course updated successfully, Course Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listCourses() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Course> coursesList = session.createQuery("from Course").list();
		for(Course p : coursesList){
			logger.info("Course List::"+p);
		}
		return coursesList;
	}

	@Override
	public Course getCourseById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Course p = (Course) session.load(Course.class, new Integer(id));
		logger.info("Course loaded successfully, Course details="+p);
		return p;
	}

	@Override
	public void removeCourse(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Course p = (Course) session.load(Course.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Course deleted successfully, person details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listMyCourses(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Course> coursesList = session.createQuery("select c from Course c INNER JOIN c.students stu where stu.id = '"+userName+"'").list();
		for(Course p : coursesList){
			logger.info("My Courses List::"+p);
		}
		return coursesList;
	}
}
