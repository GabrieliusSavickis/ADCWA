package com.example.demo.repositories;

import com.example.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findBySid(String sid);
}
