package com.example.demo.login.store.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	@Query("SELECT ur FROM UserRole ur WHERE ur.user = ?1")
	List<UserRole> authoritiesOf(List<User> users);
	
	@Query("Select u FROM UserRole u WHERE u.role.id = 2 or u.role.id = 3")
	List<UserRole> findAllAdminOrEmployees();
	
	@Query("Select u FROM UserRole u WHERE u.role.id = 1")
	List<UserRole> findAllUsers();
}
