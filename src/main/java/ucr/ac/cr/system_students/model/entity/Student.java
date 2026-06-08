package ucr.ac.cr.system_students.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students_tb")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String carnet;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Student() {
    }

    public Student(String carnet, String name, int age) {
        this.carnet = carnet;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}