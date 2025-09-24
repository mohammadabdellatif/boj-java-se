package com.bankofjordan.services.repository;

import com.bankofjordan.services.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByCoursesName(String coursesName);

    List<Student> findByNameContains(String name);

    List<Student> findByNameContainsAndDepartmentNameContains(String studentName, String departmentName);
}
