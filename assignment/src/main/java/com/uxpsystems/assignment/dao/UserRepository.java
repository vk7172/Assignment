package com.uxpsystems.assignment.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.entity.Status;
import com.uxpsystems.assignment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.userName = ?2, u.password = ?3, u.status= ?4 where u.id = ?1")
	void updateUser(long l, String userName, String password, Status status);
}
