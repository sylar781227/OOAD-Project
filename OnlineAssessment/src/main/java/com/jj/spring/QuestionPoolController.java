package com.jj.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jj.spring.model.Attributable;
import com.jj.spring.model.Question;
import com.jj.spring.model.QuestionPool;
import com.jj.spring.model.QuestionProp;
import com.jj.spring.service.QuestionPoolService;

@Controller
public class QuestionPoolController {
	
	private QuestionPoolService questionPoolService;
	
	@Autowired(required=true)
	@Qualifier(value="questionPoolService")
	public void setQuestionPoolService(QuestionPoolService qps){
		this.questionPoolService = qps;
	}
	
	@RequestMapping("/admin/pools")
	public String displayPoolsPage(Model model)
	{
		model.addAttribute("questionPools", questionPoolService.getQuestionPools());
		model.addAttribute("questionPool", new QuestionPool());
		return "pools";
	}
	
	@RequestMapping("/admin/pools/add")
	public String createNewQuestionPool(@ModelAttribute("questionPool") QuestionPool qp, RedirectAttributes redir)
	{
		if(qp.getTitle().equals(""))
		{
		   redir.addFlashAttribute("missingQuestionPoolTitle", "Please enter a name for your question pool");
		   return "redirect:/pools";
		}
		
		questionPoolService.addQuestionPool(qp);
		return "redirect:/admin/pools";
	}
	
	@RequestMapping("/admin/pools/edit/{id}")
	public String editQuestionPool(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("pool", questionPoolService.getPoolById(id));
		return "questionpoolview";
	}
	
	@RequestMapping("/admin/pools/{id}/addQuestion")
	public String addQuestionToPool(@PathVariable("id") int id, Model model)
	{	
		model.addAttribute("poolID", id);
		model.addAttribute("edit", false);
		if(!model.containsAttribute("questionProp"))
		{
			QuestionProp qp = new QuestionProp();
			qp.setQues(new Question());
			model.addAttribute("questionProp", qp);
		}
		
		return "addquestiontopool";
	}
	
	@RequestMapping("/admin/pools/{id}/edit/{qp_id}")
	public String viewQuestionInPool(@PathVariable("id") int poolID, Model model,@PathVariable("qp_id") int qp_id)
	{	
		model.addAttribute("poolID", poolID);
		model.addAttribute("edit", true);
		QuestionProp qp = questionPoolService.getQuestionPropById(qp_id);
		model.addAttribute("questionProp", qp);
		
		return "addquestiontopool";
	}

	private boolean checkForMissingFields(Attributable attributable, RedirectAttributes redir)
	{
		boolean fieldsMissing = false;
		HashMap<String, Object> attributes = attributable.listOfAttributes();
		for (Map.Entry<String, Object> entry : attributes.entrySet()) 
		{
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    
		    if(key.equals("difficulty"))
		    {
		    	int difficultyValue = ((Integer)value).intValue();
		    	if(difficultyValue == 0)
		    	{
		    		redir.addFlashAttribute("missingDifficultyMessage", "Please enter a difficulty in the range 1-10");
		    		fieldsMissing = true;
		    	}
		    }
		    else if(key.equals("section"))
		         {
		    		String section = (String)value;
		    		if(section.equals(""))
		    		{
		    			redir.addFlashAttribute("missingSectionMessage", "Please enter a section");
		    			fieldsMissing = true;
		    		}
		         }
		    else if(key.equals("course"))
		    	 {
			    	String course = (String)value;
		    		if(course.equals(""))
		    		{
		    			redir.addFlashAttribute("missingCourseMessage", "Please enter a course");
		    			fieldsMissing = true;
		    		}
		    	 }
		    else if(key.equals("title"))
	    	 {
		    	String title = (String)value;
	    		if(title.equals(""))
	    		{
	    			redir.addFlashAttribute("missingTitleMessage", "Please enter a question title");
	    			fieldsMissing = true;
	    		}
	    	 }
		    else if(key.equals("description"))
	    	 {
		    	String description = (String)value;
	    		if(description.equals(""))
	    		{
	    			redir.addFlashAttribute("missingDescriptionMessage", "Please enter a question description");
	    			fieldsMissing = true;
	    		}
	    	 }
		    else if(key.equals("codetemplate"))
	    	 {
		    	String codetemplate = (String)value;
	    		if(codetemplate.equals(""))
	    		{
	    			redir.addFlashAttribute("missingCodetemplateMessage", "Please enter a code template");
	    			fieldsMissing = true;
	    		}
	    	 }
		    else if(key.equals("submitCodeClassName"))
	    	 {
		    	String submitCodeClassName = (String)value;
	    		if(submitCodeClassName.equals(""))
	    		{
	    			redir.addFlashAttribute("missingSubmitCodeClassNameMessage", "Please enter a code template class name");
	    			fieldsMissing = true;
	    		}
	    	 }
		    else if(key.equals("testcase"))
	    	 {
		    	String testcase = (String)value;
	    		if(testcase.equals(""))
	    		{
	    			redir.addFlashAttribute("missingTestCaseMessage", "Please enter the test case code");
	    			fieldsMissing = true;
	    		}
	    	 }
		    else if(key.equals("score"))
	    	 {
		    	int score = (Integer)value;
	    		if(score == 0)
	    		{
	    			redir.addFlashAttribute("missingScoreMessage", "Please enter the total possible score for this question");
	    			fieldsMissing = true;
	    		}
	    	 }
		 }
		 return fieldsMissing;
	}
	
	private void rememberFields(QuestionProp qp, Question question, RedirectAttributes redir)
	{
		QuestionProp qp1 = new QuestionProp();
		qp1.setDifficulty(qp.getDifficulty());
		qp1.setCourse(qp.getCourse());
		qp1.setSection(qp.getSection());
		Question question1 = new Question();
		question1.setCodetemplate(question.getCodetemplate());
		question1.setDescription(question.getDescription());
		question1.setScore(question.getScore());
		question1.setSubmitCodeClassName(question.getSubmitCodeClassName());
		question1.setTestcase(question.getTestcase());
		question1.setTitle(question.getTitle());
		qp.setQues(question1);
		redir.addFlashAttribute("questionProp", qp1);
	}
	
	@RequestMapping("/admin/pools/{id}/saveQuestion")
	public String saveQuestionToPool(@ModelAttribute("qp") QuestionProp qp,@PathVariable("id") int poolID, Model model, RedirectAttributes redir){
		
		HashMap<String, Object> questionPropAttributes = qp.listOfAttributes();
		
		Question question = qp.getQues();
		
		if(checkForMissingFields(question, redir) == true)
		{
			checkForMissingFields(qp, redir);
			rememberFields(qp, question, redir);
			return "redirect:/pools/"+poolID+"/addQuestion";
		}
		
		if(checkForMissingFields(qp, redir) == true)
		{
			checkForMissingFields(question, redir);
			rememberFields(qp, question, redir);
			return "redirect:/pools/"+poolID+"/addQuestion";
		}
		
		questionPoolService.addQuestionToPool(poolID,qp);
		
		return "redirect:/admin/pools/edit/"+poolID;
	}
	
	@RequestMapping("/admin/pools/{id}/editQuestion")
	public String editQuestionInPool(@ModelAttribute("qp") QuestionProp qp,@PathVariable("id") int poolID, Model model){
		
		questionPoolService.editQuestionInPool(poolID,qp);
		
		return "redirect:/admin/pools/edit/"+poolID;
	}
}
