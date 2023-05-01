package com.example.demo.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    List<Lecturer> findAll();
    Optional<Lecturer> findByLid(String lid);
    List<Lecturer> findByTaxBandAndSalaryScaleGreaterThan(String taxBand, double salaryScale);
    List<Lecturer> findBySalaryScaleGreaterThan(int salaryScale);
    List<Lecturer> findByTaxBand(String taxBand); 
}