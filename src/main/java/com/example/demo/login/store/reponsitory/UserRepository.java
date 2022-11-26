package com.example.demo.login.store.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.login.store.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT ur.user  FROM UserRole ur WHERE ur.role.id = (2, 3)")
	List<User> getAdministrators();

	public User findByUsernameEquals(String username);
	
}
