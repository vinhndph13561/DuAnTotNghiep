package com.example.demo.bill.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="suppliers")
public class Supplier {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

	@Column(name = "provider_name")
	private String providerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private String phonerNumber;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "status")
	private int status;
}
