package com.vti.spring.entity.form;

public class AccountFormCreate {
	private String username;
	private String email;
	private int departmentId;
	private int positonId;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getPositonId() {
		return positonId;
	}
	public void setPositonId(int positonId) {
		this.positonId = positonId;
	}
	
	
}
