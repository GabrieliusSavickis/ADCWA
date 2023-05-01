package com.example.demo.models;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }
    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<Lecturer> createLecturer(@RequestBody @Valid Lecturer lecturer) {
        // Check for presence of lid and name
        if (lecturer.getLid() == null || lecturer.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lecturer must have a lid and name");
        }

        // Check for absence of id
        if (lecturer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lecturer id should not be provided");
        }

        // Save the lecturer to the database
        Lecturer savedLecturer = lecturerRepository.save(lecturer);

        // Return the saved lecturer with a 201 CREATED status
        return new ResponseEntity<>(savedLecturer, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{lid}")
    public ResponseEntity<Object> updateLecturer(@PathVariable String lid, @RequestBody Lecturer lecturer) {

        Optional<Lecturer> lecturerOptional = lecturerRepository.findByLid(lid);

        if (!lecturerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Lecturer existingLecturer = lecturerOptional.get();

        // Validate request body
        if (lecturer.getId() != null || lecturer.getLid() != null) {
            return ResponseEntity.badRequest().body("The Lecturer object must not contain id or lid");
        }
        if (lecturer.getName() == null) {
            return ResponseEntity.badRequest().body("The Lecturer object must contain name");
        }

        // Update the existing lecturer with the new attributes
        existingLecturer.setName(lecturer.getName());
        existingLecturer.setTaxBand(lecturer.getTaxBand());
        existingLecturer.setSalaryScale(lecturer.getSalaryScale());

        // Save the updated lecturer to the database
        Lecturer updatedLecturer = lecturerRepository.save(existingLecturer);

        return ResponseEntity.status(HttpStatus.OK).body(updatedLecturer);
    }
}

