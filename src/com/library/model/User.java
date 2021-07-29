package com.library.model;

public class User {

	private int userId;
	private String userName;
	private String contactInfo;

	public User(int userId, String userName, String contactInfo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.contactInfo = contactInfo;
	}

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", contactInfo=" + contactInfo + "]";
	}

}
