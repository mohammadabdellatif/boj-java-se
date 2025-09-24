package com.bankofjordan.services.entities.examples;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "students")
@Entity
public class Student implements Serializable {

    @Id
    private Long id;

    @Column(name = "stud_number", unique = true, nullable = false)
    private String number;

    @Column(name = "stud_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "std_dept_fk"))
    private Department department;

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "std_id", foreignKey = @ForeignKey(name = "student_courses_std_id")),
            inverseJoinColumns = @JoinColumn(name = "crs_id", foreignKey = @ForeignKey(name = "student_courses_crs_id")))
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(number, student.number) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name);
    }
}
