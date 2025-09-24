package com.bankofjordan.services.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "departments", indexes = {
        @Index(name = "idx_dept_nam", columnList = "dept_name", unique = true)
})
@Entity
public class Department implements Serializable {
    @Id
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "dept_name", nullable = false, length = 500)
    private String name;

    @Lob
    @Column(name = "dept_desc", nullable = false)
    private String description;

    @OneToMany(mappedBy = "department")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Department that = (Department) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
