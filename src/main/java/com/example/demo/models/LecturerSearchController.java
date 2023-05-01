package com.example.demo.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LecturerSearchController {
	
	@Autowired
    private LecturerRepository lecturerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/lecturer/search")
    public ResponseEntity<Object> searchLecturers(
            @RequestParam(value = "taxBand", required = false) String taxBand,
            @RequestParam(value = "salaryScale", required = false) Integer salaryScale) {
        List<Lecturer> lecturers;

        // Check if both parameters are present
        if (taxBand != null && salaryScale != null) {
            lecturers = lecturerRepository.findByTaxBandAndSalaryScaleGreaterThan(taxBand, salaryScale);
        } else if (taxBand != null) {
            lecturers = lecturerRepository.findByTaxBand(taxBand);
        } else if (salaryScale != null) {
            lecturers = lecturerRepository.findBySalaryScaleGreaterThan(salaryScale);
        } else {
            // Return bad request if no parameters are present
            return ResponseEntity.badRequest().body("At least one search parameter is required.");
        }

        // Return not found if no lecturers match the search criteria
        if (lecturers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No lecturers found for the specified search criteria.");
        }

        return ResponseEntity.ok(lecturers);
    }
}
