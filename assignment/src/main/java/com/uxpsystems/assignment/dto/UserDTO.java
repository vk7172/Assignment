package com.uxpsystems.assignment.dto;

import java.util.Date;

public class UserDTO {


	private String userName;
	private String password;
	private String status;
	private Date createdTime;

		public UserDTO(){}
		 
	
		
		public UserDTO(String userName, String password, String status) {
			super();
			this.userName = userName;
			this.password = password;
			this.status = status;
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
		
		
	

}
