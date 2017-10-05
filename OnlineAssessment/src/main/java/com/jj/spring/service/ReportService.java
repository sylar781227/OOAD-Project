package com.jj.spring.service;

import java.util.List;

import com.jj.spring.model.DisplayResult;

public interface ReportService {

	public List<DisplayResult> getResultsforExam(int courseID, int examID);
	
}
