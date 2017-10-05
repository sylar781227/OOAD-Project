package com.jj.spring.dao;

import java.util.List;

import com.jj.spring.model.Result;

public interface ResultDAO {

	public void addResult(Result p);
	public void updateResult(Result p);
	public List<Result> listResults();
	public Result getResultById(int id);
	public void removeResult(int id);
	void saveupdateResult(Result p);
	public boolean hasExamStarted(String username, int exam_id);
	public List<Result> getResults(String username, int exam_id);
	public List<Result> getResultsForExam(int examID);
}
