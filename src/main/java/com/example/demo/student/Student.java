package com.example.demo.student;

import java.time.LocalDate;

public record Student(Long id, String name, String email, LocalDate dob) {};

//public class Student {
//	private Long id;
//	private String name;
//	private String email;
//	private LocalDate dob;
//	
//}
