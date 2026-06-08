package ucr.ac.cr.system_students.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ucr.ac.cr.system_students.model.dto.StudentRequestDTO;
import ucr.ac.cr.system_students.model.dto.StudentResponseDTO;
import ucr.ac.cr.system_students.model.entity.Course;
import ucr.ac.cr.system_students.model.entity.Student;
import ucr.ac.cr.system_students.repository.StudentRepository;

import java.util.List;
import java.util.ArrayList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public StudentResponseDTO save(StudentRequestDTO dto) {

        if (studentRepository.existsByCarnet(dto.getCarnet())) {
            throw new RuntimeException("Ya existe un estudiante con ese carnet.");
        }

        Course course = courseService.findEntityById(dto.getCourseId());

        Student student = new Student();
        student.setCarnet(dto.getCarnet());
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);

        return toResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> responseList = new ArrayList<>();

        for (Student student : students) {
            responseList.add(toResponseDTO(student));
        }

        return responseList;
    }

    public StudentResponseDTO findByCarnet(String carnet) {
        Student student = studentRepository.findByCarnet(carnet)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));

        return toResponseDTO(student);
    }

    public void deleteByCarnet(String carnet) {
        if (!studentRepository.existsByCarnet(carnet)) {
            throw new RuntimeException("No existe un estudiante con ese carnet.");
        }

        studentRepository.deleteByCarnet(carnet);
    }

    public List<StudentResponseDTO> searchByName(String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        List<StudentResponseDTO> responseList = new ArrayList<>();

        for (Student student : students) {
            responseList.add(toResponseDTO(student));
        }

        return responseList;
    }

    public double getAverageAge() {
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Student student : students) {
            total += student.getAge();
        }

        return (double) total / students.size();
    }

    public List<StudentResponseDTO> findAdults() {
        List<Student> students = studentRepository.findByAgeGreaterThan(18);
        List<StudentResponseDTO> responseList = new ArrayList<>();

        for (Student student : students) {
            responseList.add(toResponseDTO(student));
        }

        return responseList;
    }

    private StudentResponseDTO toResponseDTO(Student student) {

        Long courseId = null;
        String courseName = "Sin curso";

        if (student.getCourse() != null) {
            courseId = student.getCourse().getId();
            courseName = student.getCourse().getName();
        }

        return new StudentResponseDTO(
                student.getId(),
                student.getCarnet(),
                student.getName(),
                student.getAge(),
                courseId,
                courseName
        );
    }
}