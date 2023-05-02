package com.example.demo.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public record Customer(
	

		int customer_id,
		String first_name,
		String last_name,
		String birth_date,
		String phone,
		String address,
		String city,
		String sate,
		int points) {};
