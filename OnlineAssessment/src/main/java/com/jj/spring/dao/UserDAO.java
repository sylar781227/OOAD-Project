package com.jj.spring.dao;

import java.util.List;

import com.jj.spring.model.User;

public interface UserDAO {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserByUserName(String userName);
	public void removeUser(int id);
}
