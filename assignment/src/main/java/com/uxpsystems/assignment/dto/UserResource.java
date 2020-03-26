package com.uxpsystems.assignment.dto;

import java.util.Date;



public class UserResource{
	
	private Long userId;
	private String userName;
	private String password;
	private String status;
	private Date createdTime;
	 
		public UserResource(){}
		 
	
		
		public UserResource(Long userId, String userName, String password, String status, Date createdTime) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.password = password;
			this.status = status;
			this.createdTime = createdTime;
		}



		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}



	



		public Date getCreatedTime() {
			return createdTime;
		}



		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}



		public Long getUserId() {
			return userId;
		}



		public void setUserId(Long userId) {
			this.userId = userId;
		}



		

}
