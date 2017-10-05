package com.jj.spring.service;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jj.spring.dao.CourseDAO;
import com.jj.spring.dao.ExamDAO;
import com.jj.spring.dao.UserDAO;
import com.jj.spring.model.Course;
import com.jj.spring.model.Exam;
import com.jj.spring.model.User;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseDAO courseDAO;
	
	private UserDAO userDAO;
	
	private ExamDAO examDAO;
	
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}
	
	@Override
	@Transactional
	public void addCourse(Course p) {
		this.courseDAO.addCourse(p);
	}

	@Override
	@Transactional
	public void updateCourse(Course p) {
		this.courseDAO.updateCourse(p);
	}

	@Override
	@Transactional
	public List<Course> listCourses() {
		return this.courseDAO.listCourses();
	}

	@Override
	@Transactional
	public Course getCourseById(int id) {
		return this.courseDAO.getCourseById(id);
	}

	@Override
	@Transactional
	public void removeCourse(int id) {
		this.courseDAO.removeCourse(id);
	}

	@Override
	@Transactional
	public void addStudentToCourse(int id, User u) {
		
		Course course = this.getCourseById(id);
		System.out.println(course);
		course.getStudents().add(u);
		updateCourse(course);
	}
	
	@Override
	@Transactional
	public void addExamToCourse(int id, Exam e) {
		
		Course course = this.getCourseById(id);
		System.out.println(course);
		
		course.getExams().add(e);
		updateCourse(course);
	}

	@Override
	@Transactional
	public List<Course> listMyCourses(String userName) {
		return this.courseDAO.listMyCourses(userName);
	}

	@Override
	@Transactional
	public void handleBulkUpload(MultipartFile file,int course_id) {
		try {
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
		    Row row;
		    Cell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }
		    
		    String firstName="", lastName="", netID="";
		    for(int r = 1; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell(c);
		                if(cell != null) {
		                	switch(c)
		                	{
		                	 case 0: lastName = cell.toString();
		                	 case 1: firstName = cell.toString();
		                	 case 2: continue;
		                	 case 3: netID = cell.toString();
		                	}
		                   
		                }
		            }
		            
		            User user = userDAO.getUserByUserName(netID);
		            
		            if(user!=null){ // guy is in the system
		            	
		            	System.out.println("Guy in system");
		            	
		            	Course course = courseDAO.getCourseById(course_id);
		            	
		            	if(!course.getStudents().contains(user)){
		            		addStudentToCourse(course_id, user);
		            	}
		            	
		            }else{
		            	if(!StringUtils.isEmpty(netID))
		            	{
			            	// send mail and add to course
			            	System.out.println("Guy not in system");
			            	
			            	String tempPassword = UUID.randomUUID().toString().substring(0,9)+"x";
				            sendChangePasswordEmail(firstName, netID, tempPassword);
			            	
			            	User newUser = new User(netID,firstName,lastName,tempPassword);
			            	//userDAO.addUser(newUser);
			            	
			            	addStudentToCourse(course_id, newUser);
		            	}
		            }
		            
		        }
		    }
		    workbook.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
	
	private void sendChangePasswordEmail(String firstName, String netID, String tempPassword)
	{   
				final String username = "faragopeter8@gmail.com";
		        final String password = "Nyuszkalacs2!";
                
		        Properties props = new Properties();
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.host", "smtp.gmail.com");
		        props.put("mail.smtp.port", "587");

		        Session session1 = Session.getInstance(props,
		          new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		            }
		          });

		        try {
		            Message message = new MimeMessage(session1);
		            message.setFrom(new InternetAddress("faragopeter8@gmail.com"));
		            message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(netID+"@utdallas.edu"));
		            message.setSubject("Test email");
		            
		            System.out.println("I am ");
		            
		            message.setText("Dear "+firstName+",\n\n"
		                             + "Your CSExamApp account has been created. Your temporary password is: " + tempPassword + "\n" 
		                             + "Please click on this link to change your password:\n"
		                             + "http://localhost:8080/SpringMVCHibernate/changePassword/" + netID + "\n\nSincerely,\nCSExamApp Team");

		            Transport.send(message);

		        } catch (MessagingException e) {
		            throw new RuntimeException(e);
		        }
	 }

	@Override
	@Transactional
	public void removeStudentFromCourse(int courseID, String userName) {
		Course course = courseDAO.getCourseById(courseID);
		User user = userDAO.getUserByUserName(userName);
		course.getStudents().remove(user);
		updateCourse(course);
	}

	@Override
	@Transactional
	public void removeExamFromCourse(int courseID, int examID) {
		Course course = courseDAO.getCourseById(courseID);
		Exam e = examDAO.getExamById(examID);
		course.getExams().remove(e);
		updateCourse(course);
	}
}
