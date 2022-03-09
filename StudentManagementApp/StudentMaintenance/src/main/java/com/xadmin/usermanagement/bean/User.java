package com.xadmin.usermanagement.bean;

public class User {

	private int id;
	private String name;
	private String email;
	private String Department;
	
	public User(String name, String email, String department) {
		super();
		this.name = name;
		this.email = email;
		Department = department;
	}
	public User(int id, String name, String email, String department) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		Department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	
}
