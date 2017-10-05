package com.jj.spring.model;

public class Stat {
	
	int compileStatus;
	String errorMessage;
	int score;
	
	public int getCompileStatus() {
		return compileStatus;
	}
	public void setCompileStatus(int compileStatus) {
		this.compileStatus = compileStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
