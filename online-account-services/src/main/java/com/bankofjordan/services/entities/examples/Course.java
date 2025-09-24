package com.bankofjordan.services.entities.examples;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "courses")
@Entity
public class Course implements Serializable {
    @Id
    private Integer id;

    @Column(name = "crs_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(department, course.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }
}
