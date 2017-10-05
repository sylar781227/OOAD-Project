package com.jj.spring.model;

public class DisplayResult {
	
	private Question ques;
	
	private Result r;
	
	public DisplayResult(){
		
	}
	
	public DisplayResult(Question q,Result r){
		this.ques = q;
		this.r = r;
	}
	
	public Question getQues() {
		return ques;
	}

	public void setQues(Question ques) {
		this.ques = ques;
	}

	public Result getR() {
		return r;
	}

	public void setR(Result r) {
		this.r = r;
	}
	
}
