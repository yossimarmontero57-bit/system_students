package ucr.ac.cr.system_students.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.system_students.model.dto.StudentRequestDTO;
import ucr.ac.cr.system_students.model.dto.StudentResponseDTO;
import ucr.ac.cr.system_students.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        try {
            StudentResponseDTO response = studentService.save(studentRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{carnet}")
    public ResponseEntity<?> getStudentByCarnet(@PathVariable String carnet) {
        try {
            StudentResponseDTO response = studentService.findByCarnet(carnet);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{carnet}")
    public ResponseEntity<?> deleteStudent(@PathVariable String carnet) {
        try {
            studentService.deleteByCarnet(carnet);
            return ResponseEntity.ok("Estudiante eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.searchByName(name));
    }

    @GetMapping("/average-age")
    public ResponseEntity<?> getAverageAge() {
        double average = studentService.getAverageAge();
        return ResponseEntity.ok(average);
    }

    @GetMapping("/adults")
    public ResponseEntity<?> getAdults() {
        return ResponseEntity.ok(studentService.findAdults());
    }
}
