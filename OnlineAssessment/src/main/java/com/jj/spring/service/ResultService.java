package com.jj.spring.service;

import com.jj.spring.model.Result;
import com.jj.spring.model.Stat;

public interface ResultService {

	public String addResult(Result ques);
	public void updateResult(Result ques);
	public Result getResultById(int studentID, int questionID);
	public void removeResult(int studentID, int questionID);
	public Stat compileGenResult(Result r);
	
}
