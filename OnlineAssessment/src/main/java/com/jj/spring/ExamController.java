package com.jj.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jj.spring.model.DisplayResult;
import com.jj.spring.model.Exam;
import com.jj.spring.model.Question;
import com.jj.spring.model.Result;
import com.jj.spring.service.ExamService;

@Controller
public class ExamController {
	
	private ExamService examService;
	
	@Autowired(required=true)
	@Qualifier(value="examService")
	public void setExamService(ExamService ps){
		this.examService = ps;
	}
	
	@RequestMapping(value = "/admin/exams", method = RequestMethod.GET)
	public String listExams(Model model) {
		model.addAttribute("exam", new Exam());
		model.addAttribute("listExams", this.examService.listExams());
		return "exams";
	}
	
	//For add and update exam both
	@RequestMapping(value= "/admin/exam/add", method = RequestMethod.POST)
	public String addExam(@ModelAttribute("exam") Exam p){
		
		if(p.getId() == 0){
			//new exam, add it
			this.examService.addExam(p);
		}else{
			//existing exam, call update
			this.examService.updateExam(p);
		}
		
		return "redirect:/admin/exams";
		
	}
	
	@RequestMapping("/admin/exam/remove/{id}")
    public String removeExam(@PathVariable("id") int id){
        this.examService.removeExam(id);
        return "redirect:/admin/exams";
    }
 
    @RequestMapping("/admin/course/{courseid}/exam/{examid}")
    public String editExam(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,Model model){
    	        
        model.addAttribute("question", new Question());
        model.addAttribute("courseid", course_id);
        model.addAttribute("examid", exam_id);
        model.addAttribute("questions", this.examService.listQuestions(course_id,exam_id));
        
        return "examview";
    }
    
    @RequestMapping("/course/{courseid}/openexam/{examid}/uid/{uid}")
    public String openExam(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,Model model,@PathVariable("uid") String username){
        
        
    	model.addAttribute("courseid", course_id);
        model.addAttribute("examid", exam_id);
        model.addAttribute("userid", username);
        // Exam is done then display old score and submitted code
        // get exam status for this user
        boolean hasStarted = examService.hasExamStarted(username,exam_id);
        // is not started
        System.out.println("Exam Started - "+hasStarted);
        if(!hasStarted){
        	model.addAttribute("started", false);
        	model.addAttribute("submission",new Result());
        	Exam e = this.examService.getExamById(exam_id);
            model.addAttribute("timeLimit", Integer.parseInt(e.getTimeLimit()));
            model.addAttribute("questions", e.getQuestions());
        
        }else{
        	// if started return score
        	model.addAttribute("started", true);
        	
        	List<DisplayResult> dresults = this.examService.getResults(username,exam_id);
        	int total = 0;
        	
        	for(DisplayResult dr:dresults){
        		total+=dr.getR().getScore();
        	}
        	
        	model.addAttribute("dresults", this.examService.getResults(username,exam_id));
        	model.addAttribute("total", total);
        }
        return "myexamview";
    }
    
   @RequestMapping("/admin/course/{courseid}/exam/{examid}/addQuestion")
   public String addQuestion(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,
		   @ModelAttribute("question") Question ques){ 
	    this.examService.addQuestionToExam(exam_id,ques);
        return "redirect:/admin/course/"+course_id+"/exam/"+exam_id;
    }
   
   
   @RequestMapping("/admin/course/{courseid}/exam/{examid}/submitQuestionById")
   public String addQuestionById(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,
		   @ModelAttribute("question") Question ques){
	   
	    int q_id = ques.getId();
	    this.examService.addQuestionToExamByID(exam_id,q_id);
        return "redirect:/admin/course/"+course_id+"/exam/"+exam_id;
    }
   
   @RequestMapping("/admin/course/{courseid}/exam/{examid}/deleteques/{quesid}")
   public String removeQuestion(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,@PathVariable("quesid") int ques_id){
		   
	   this.examService.removeQuestionFromExam(exam_id,ques_id);
	   
	   return "redirect:/admin/course/"+course_id+"/exam/"+exam_id;
   }
    
}
