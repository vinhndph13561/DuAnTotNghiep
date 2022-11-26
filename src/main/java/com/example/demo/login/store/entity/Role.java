package com.example.demo.login.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "role_name")
	private String roleName;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<UserRole> userRoles;
}
