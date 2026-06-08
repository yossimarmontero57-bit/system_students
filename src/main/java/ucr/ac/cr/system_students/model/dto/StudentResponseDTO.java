package ucr.ac.cr.system_students.model.dto;

public class StudentResponseDTO {

    private Long id;
    private String carnet;
    private String name;
    private int age;
    private Long courseId;
    private String courseName;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(Long id, String carnet, String name, int age, Long courseId, String courseName) {
        this.id = id;
        this.carnet = carnet;
        this.name = name;
        this.age = age;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}