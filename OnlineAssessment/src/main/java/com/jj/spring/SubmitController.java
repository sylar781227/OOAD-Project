package com.jj.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jj.spring.model.Result;
import com.jj.spring.service.ResultService;

@Controller
public class SubmitController {
	
	private ResultService resultService;
	
	@Autowired(required=true)
	@Qualifier(value="resultService")
	public void setSubmitService(ResultService rs){
		this.resultService = rs;
	}
	
	@RequestMapping(value= "/course/{courseid}/exam/{examid}/quessubmit/{quesid}", method = RequestMethod.POST)
	@ResponseBody
	public String submitCode(@ModelAttribute("submission") Result r){
		
		String resultMsg = resultService.addResult(r);
		
		return resultMsg;
	}
	
}
