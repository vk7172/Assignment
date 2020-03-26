package com.uxpsystems.assignment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.uxpsystems.assignment.Status;

@Entity
@Table(name = "employee")
public class User {
	
	private long id;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Temporal(TemporalType.TIMESTAMP)
	    private Date createdTime;

	
	public User(){}
	
	public User(long id, String userName, String password, Status status) {
		
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.setStatus(status);
	}
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="e_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	
	public void setStatus(Status status) {
		this.status = status;
	}


	
	    public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

		@PrePersist
	    protected void onCreate() {
	        createdTime = new Date();
	    }

	
}
