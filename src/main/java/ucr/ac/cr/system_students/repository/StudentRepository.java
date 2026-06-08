package ucr.ac.cr.system_students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucr.ac.cr.system_students.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByCarnet(String carnet);

    boolean existsByCarnet(String carnet);

    void deleteByCarnet(String carnet);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByAgeGreaterThan(int age);
}
