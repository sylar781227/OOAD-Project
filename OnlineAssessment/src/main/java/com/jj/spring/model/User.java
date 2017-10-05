package com.jj.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="USER")
public class User {
	
	private String userName;
	private boolean admin;
	private String firstName;
	private String lastName;
	private String password;
	
	public User(){
		
	}
	
	public User(String userName,String firstName,String lastName,String password){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public boolean getAdmin()
	{
		return admin;
	}
	
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	@Id
	@Column(name="STUDENT_ID")
	public String getUserName()
	{
		return userName;
	}
		public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return "id="+userName+", fname="+firstName+", lname="+lastName;
	}

}
