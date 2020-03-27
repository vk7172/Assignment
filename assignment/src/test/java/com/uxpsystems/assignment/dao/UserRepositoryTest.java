package com.uxpsystems.assignment.dao;





import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.uxpsystems.assignment.entity.Status;
import com.uxpsystems.assignment.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	 
	 @Autowired 
	 private UserRepository repository;

	 	@Test
	    public void should_find_no_user_if_repository_is_empty() {
	        Optional<User> user = repository.findById(1L);
	       assertFalse(user.isPresent());
	    }
	 
	 	@Test
	    public void should_find_user_by_id() {
		  User user = repository.save(new User(1L, "TestUser1", "password", Status.ACTIVATED));
	
		  Optional<User> savedUser = repository.findById(1L);

	        assertTrue(savedUser.isPresent());
	     	assertSame(savedUser.get(), user);
	    }
	 	
	 	
	 	@Test
	 	public void should_save_user(){
	 		 User user = repository.save(new User(1L, "TestUser1", "password", Status.ACTIVATED));
	 		 
	 		assertThat(user).hasFieldOrPropertyWithValue("id", 1L);
	        assertThat(user).hasFieldOrPropertyWithValue("userName", "TestUser1");
	        assertThat(user).hasFieldOrPropertyWithValue("password", "password");
	        assertThat(user).hasFieldOrPropertyWithValue("status", Status.ACTIVATED);
	        assertThat(user.getCreatedTime()).isNotNull();


	 	}
	 	
		@Test
	 	public void should_update_user(){
	 		 User user = repository.save(new User(1L, "TestUser1", "password", Status.ACTIVATED));
	 		 
	 		 
	 		 repository.updateUser(1L, "TestUser1", "password", Status.DEACTIVATED);

	 		
	 		Optional<User> savedUser = repository.findById(1L);

	 		
	 		assertThat(savedUser.get()).hasFieldOrPropertyWithValue("id", 1L);
	        assertThat(savedUser.get()).hasFieldOrPropertyWithValue("userName", "TestUser1");
	        assertThat(savedUser.get()).hasFieldOrPropertyWithValue("password", "password");
	        assertThat(savedUser.get()).hasFieldOrPropertyWithValue("status", Status.DEACTIVATED);

	 	}
		
	 	
			@Test
		 	public void should_delete_user(){
		 		 User user = repository.save(new User(1L, "TestUser1", "password", Status.ACTIVATED));
		 		 
		 		 repository.deleteById(1L);
		 		 
		 		 Optional<User> user1 = repository.findById(1L);
			      
		 		assertThat(user1).isEmpty();
		 		
			}
	
	
}
