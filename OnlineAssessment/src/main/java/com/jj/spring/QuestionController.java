package com.jj.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jj.spring.model.Question;
import com.jj.spring.service.QuestionService;

@Controller
public class QuestionController {
	
	private QuestionService questionService;
	
	@Autowired(required=true)
	@Qualifier(value="questionService")
	public void setQuestionService(QuestionService ps){
		this.questionService = ps;
	}
	
	@RequestMapping("/admin/question/remove/{id}")
    public String removeQuestion(@PathVariable("id") int id){
		
        this.questionService.removeQuestion(id);
        return "redirect:/admin/questions";
    }
	
	@RequestMapping("/admin/course/{courseid}/exam/{examid}/ques/{quesid}/update")
    public String updateQuestion(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,
    		@PathVariable("quesid") int ques_id,@ModelAttribute("question") Question ques){
			
		   this.questionService.updateQuestion(ques);
		
		 return "redirect:/admin/course/"+course_id+"/exam/"+exam_id+"/ques/"+ques_id;
	}
	
    @RequestMapping("/admin/course/{courseid}/exam/{examid}/ques/{quesid}")
    public String editQuestion(@PathVariable("courseid") int course_id,@PathVariable("examid") int exam_id,
    		@PathVariable("quesid") int ques_id, Model model){
        model.addAttribute("question", this.questionService.getQuestionById(ques_id));
        
        model.addAttribute("courseid", course_id);
        model.addAttribute("examid", exam_id);
        model.addAttribute("quesid", ques_id);
        
        return "questionview";
    }	
}
