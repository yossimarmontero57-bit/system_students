package ucr.ac.cr.system_students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.system_students.model.entity.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    boolean existsByCode(String code);
}
