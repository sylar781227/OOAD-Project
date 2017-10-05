package com.jj.spring.model;

public class ChangePassword {
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
	public String getNetID() {
		return netID;
	}
	public void setNetID(String netID) {
		this.netID = netID;
	}

	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;
	private String netID;
}