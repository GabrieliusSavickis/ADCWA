package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.StudentRepository;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/students")
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<Object> result = new ArrayList<Object>();
        for (Student student : students) {
            Map<String, Object> studentData = new HashMap<String, Object>();
            studentData.put("id", student.getId());
            studentData.put("sid", student.getSid());
            studentData.put("name", student.getName());

            List<Map<String, Object>> modules = new ArrayList<Map<String, Object>>();
            for (Module module : student.getModules()) {
                Map<String, Object> moduleData = new HashMap<String, Object>();
                moduleData.put("id", module.getId());
                moduleData.put("mid", module.getMid());
                moduleData.put("name", module.getName());
                moduleData.put("credits", module.getCredits());
                moduleData.put("level", module.getLevel());

                Map<String, Object> lecturerData = new HashMap<String, Object>();
                Lecturer lecturer = module.getLecturer();
                lecturerData.put("id", lecturer.getId());
                lecturerData.put("name", lecturer.getName());
                lecturerData.put("taxBand", lecturer.getTaxBand());
                lecturerData.put("salaryScale", lecturer.getSalaryScale());
                moduleData.put("lecturer", lecturerData);

                modules.add(moduleData);
            }
            studentData.put("modules", modules);

            result.add(studentData);
        }

        return ResponseEntity.ok(result);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/students/{sid}")
	public ResponseEntity<Object> deleteStudent(@PathVariable String sid) {
		Student student = studentRepository.findBySid(sid);
		if (student == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Student with sid " + sid + " does not exist.");
		}
		if (!student.getModules().isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Cannot delete student with sid " + sid + " because they have associated modules.");
		}
		studentRepository.delete(student);
		return ResponseEntity.ok().build();
	}
 }

