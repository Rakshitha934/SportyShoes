package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,String> {
	
	
@Query("select u from User u where u.Username=?1 and u.password=?2")
public User findByuserpwd(String username,String password);

@Query ("select u from User u where u.Username=?1")
public User findByUsername(String Username);

	
}

