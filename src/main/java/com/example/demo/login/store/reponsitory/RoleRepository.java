package com.example.demo.login.store.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.store.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
