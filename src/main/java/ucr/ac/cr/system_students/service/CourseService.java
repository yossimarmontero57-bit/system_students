package ucr.ac.cr.system_students.service;

import org.springframework.stereotype.Service;
import ucr.ac.cr.system_students.model.dto.CourseRequestDTO;
import ucr.ac.cr.system_students.model.dto.CourseResponseDTO;
import ucr.ac.cr.system_students.model.entity.Course;
import ucr.ac.cr.system_students.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO save(CourseRequestDTO dto) {
        if (courseRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Ya existe un curso con ese código.");
        }

        Course course = new Course();
        course.setCode(dto.getCode());
        course.setName(dto.getName());

        Course savedCourse = courseRepository.save(course);

        return toResponseDTO(savedCourse);
    }

    public List<CourseResponseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDTO> responseList = new ArrayList<>();

        for (Course course : courses) {
            responseList.add(toResponseDTO(course));
        }

        return responseList;
    }

    public CourseResponseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado."));

        return toResponseDTO(course);
    }

    public CourseResponseDTO findByCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado."));

        return toResponseDTO(course);
    }

    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("No existe un curso con ese ID.");
        }

        courseRepository.deleteById(id);
    }

    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getCode(),
                course.getName()
        );
    }

    public Course findEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado."));
    }
}
