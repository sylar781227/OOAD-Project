package com.jj.spring;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jj.spring.model.Course;
import com.jj.spring.model.Exam;
import com.jj.spring.model.User;
import com.jj.spring.service.CourseService;

@Controller
//@SessionAttributes("username")
public class CourseController {
	
	private CourseService courseService;
	
	@Autowired(required=true)
	@Qualifier(value="courseService")
	public void setCourseService(CourseService ps){
		this.courseService = ps;
	}
	
	@RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
	public String listCourses(@ModelAttribute("username") String username, Model model) {
		
		model.addAttribute("course", new Course());
		model.addAttribute("listCourses", this.courseService.listCourses());
		return "courses";
	}
	
	//For add and update course both
	@RequestMapping(value= "/admin/course/add", method = RequestMethod.POST)
	public String addCourse(HttpSession session, @ModelAttribute("course") Course p, RedirectAttributes redir){

		if(p.getCourseName().equals(""))
		{
			redir.addFlashAttribute("missingCourseNameMessage","Please enter a name for your course");
			return "redirect:/courses";
		}
		
		if(p.getId() == 0){
			//new course, add it
			this.courseService.addCourse(p);
		}else{
			//existing course, call update
			this.courseService.updateCourse(p);
		}
		
		return "redirect:/admin/courses";
		
	}
	
	@RequestMapping("/admin/course/remove/{id}")
    public String removeCourse(@PathVariable("id") int id){
		
        this.courseService.removeCourse(id);
        return "redirect:/admin/courses";
    }
 
    @RequestMapping("/admin/course/edit/{id}")
    public String editCourse(@PathVariable("id") int id, Model model){
    	
        model.addAttribute("course", this.courseService.getCourseById(id));
        if(!model.containsAttribute("student"))
        {
            model.addAttribute("student", new User());
        }
        if(!model.containsAttribute("exam"))
        {
        	model.addAttribute("exam", new Exam());
        }
        return "courseview";
    }
    
    @RequestMapping("/course/open/{id}")
    public String openCourse(@PathVariable("id") int id, Model model,HttpSession session){
    	String userName = (String)session.getAttribute("username");
    	model.addAttribute("userid", userName);
        model.addAttribute("course", this.courseService.getCourseById(id));
        return "mycourseview";
    }    
    
    @RequestMapping("/admin/course/{id}/addStudent")
    public String addStudent(@PathVariable("id")int id,@ModelAttribute("student") User u, RedirectAttributes redir){
    	
    	//System.out.println(u.getFirstName());
    	
    	if(u.getUserName().equals("")||u.getFirstName().equals("")||u.getLastName().equals(""))
    	{
    		if(u.getUserName().equals(""))
    		{
    			redir.addFlashAttribute("missingUsernameMessage","Please enter a username for your student");
    		}
    		
    		if(u.getFirstName().equals(""))
    		{
    			redir.addFlashAttribute("missingFirstNameMessage","Please enter a first name for your student");
    		}
    		
    		if(u.getLastName().equals(""))
    		{
    			redir.addFlashAttribute("missingLastNameMessage","Please enter a last name for your student");
    		}
    		User u1 = new User();
    		u1.setUserName(u.getUserName());
    		u1.setFirstName(u.getFirstName());
    		u1.setLastName(u.getLastName());
    		redir.addFlashAttribute("student", u1);
    		return "redirect:/course/edit/"+id;
    	}
    	
    	
    	
    	System.out.println(u);
    	
    	this.courseService.addStudentToCourse(id,u);
    	
    	return "redirect:/admin/course/edit/"+id;
    }
    
    @RequestMapping(value = "/admin/course/{courseID}/removeStudent/{userName}", method = RequestMethod.GET)
    public String removeStudent(@PathVariable("courseID") int courseID,@PathVariable("userName") String userName){
    
    		courseService.removeStudentFromCourse(courseID,userName);
    	
    	return "redirect:/admin/course/edit/"+courseID;
    }
    
    
    @RequestMapping(value = "/admin/uploadFile/{id}", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file,@PathVariable("id")int id) 
	{	
    	
    	this.courseService.handleBulkUpload(file,id);
    	return "redirect:/admin/course/edit/"+id;
    	
	}
        
    @RequestMapping("/admin/course/{id}/addExam")
    public String addExam(@PathVariable("id")int id,@ModelAttribute("exam") Exam e, RedirectAttributes redir){
    	
    	//System.out.println(u.getFirstName());
    	
    	if(e.getTitle().equals("")||e.getTimeLimit().equals(""))
    	{
    		if(e.getTitle().equals(""))
    		{
    			redir.addFlashAttribute("missingExamNameMessage","Please enter a name for your exam");
    		}
    		
    		if(e.getTimeLimit().equals(""))
    		{
    			redir.addFlashAttribute("missingExamTimeLimitMessage","Please enter a time limit for your exam");
    		}
    		Exam e1 = new Exam();
    		e1.setTitle(e.getTitle());
    		e1.setTimeLimit(e.getTimeLimit());
    		redir.addFlashAttribute("exam", e1);
    		return "redirect:/course/edit/"+id;
    	}
    	
    	this.courseService.addExamToCourse(id,e);
    	
    	return "redirect:/admin/course/edit/"+id;
    }
    
    @RequestMapping("/admin/course/{courseid}/remove/{examid}")
    public String removeExam(@PathVariable("courseid")int courseID,@PathVariable("examid") int examID){
    	
    	courseService.removeExamFromCourse(courseID, examID);
    	return "redirect:/admin/course/edit/"+courseID;
    }
    
    
    
    @RequestMapping(value = "/mycourses", method = RequestMethod.GET)
	public String listMyCourses(HttpSession session, Model model) {
    	String userName = (String)session.getAttribute("username");
		model.addAttribute("listMyCourses", this.courseService.listMyCourses(userName));
		return "mycourses";
	}
    
}
