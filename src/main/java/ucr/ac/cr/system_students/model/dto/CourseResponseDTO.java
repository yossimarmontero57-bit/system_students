package ucr.ac.cr.system_students.model.dto;

public class CourseResponseDTO {

    private Long id;
    private String code;
    private String name;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
