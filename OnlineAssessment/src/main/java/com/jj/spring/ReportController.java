package com.jj.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jj.spring.service.ReportService;

@Controller
public class ReportController {
	
		private ReportService reportService;
		
		@Autowired(required=true)
		@Qualifier(value="reportService")
		public void setReportService(ReportService rs){
			this.reportService = rs;
		}
		
		@RequestMapping(value = "/admin/getreport/course/{courseid}/exam/{examid}", method = RequestMethod.GET)
		public String listExams(Model model,@PathVariable("courseid") int courseID,@PathVariable("examid") int examID) {
			model.addAttribute("dresults", this.reportService.getResultsforExam(courseID, examID));
			return "report";
		}
		
		
}


