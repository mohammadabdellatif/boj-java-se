package com.bankofjordan.services;

import com.bankofjordan.services.entities.Course;
import com.bankofjordan.services.entities.Department;
import com.bankofjordan.services.entities.Student;
import com.bankofjordan.services.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableTransactionManagement
public class OnlineAccountServicesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OnlineAccountServicesApplication.class, args);

        StudentRepository studentRepository = applicationContext.getBean(StudentRepository.class);

        List<Student> byCoursesName = studentRepository.findByCoursesName("Statistics 101");
        for (Student student : byCoursesName) {
            System.out.println(student.getName());
        }

        System.out.println("done");
    }

    private static void springData1(StudentRepository studentRepository) {
        Optional<Student> student = studentRepository.findById(123);
        if (student.isPresent()) {
            Student std = student.get();
            System.out.println(std.getName());
        } else {
            System.out.println("didn't find it");
        }
    }

    private static void standardJPAAccess(ConfigurableApplicationContext applicationContext) {
        EntityManager em = applicationContext.getBean(EntityManager.class);

        PlatformTransactionManager trm = applicationContext.getBean(PlatformTransactionManager.class);
        TransactionTemplate tmpl = new TransactionTemplate(trm);

        Query query = em.createQuery("select current_date(),c.name from Course c ");

        List resultList = query.getResultList();

        for (Object o : resultList) {
            Object[] row = (Object[]) o;
            System.out.println(row[0] + ": " + row[0].getClass() + " " + row[1]);
        }
    }

    private static void fourthExample(EntityManager em) {
        Query query = em.createQuery("select s from Student s, IN(s.courses) c where lower(c.name) like '%statistics%'");
        List resultList = query.getResultList();
        for (Object o : resultList) {
            Student s = (Student) o;
            System.out.println(s.getName());
            System.out.println(s.getDepartment().getName());
        }
    }

    private static void thirdExample(EntityManager em) {
        Query query = em.createQuery("select d.id, d.name from Department d where d.id = :deptId");
        query.setParameter("deptId", 1);

        List resultList = query.getResultList();
        for (Object o : resultList) {
            Object[] row = (Object[]) o;
            System.out.println(row[0] + "," + row[1]);
        }
    }

    private static void secondExample(TransactionTemplate tmpl, EntityManager em) {
        tmpl.executeWithoutResult((s) -> {
            Student student = em.find(Student.class, 123);
            Course course = em.find(Course.class, 1);

            student.setCourses(Arrays.asList(course));
//            course.setStudents(Arrays.asList(student));

            em.persist(student);
        });
    }

    private static void firstExample(ConfigurableApplicationContext applicationContext, EntityManager em) {
        Department department = new Department();
        department.setId(2);
        department.setName("Applied Mathematics");
        department.setDescription("The department of Applied Mathematics and natural sciences");

        Student student = new Student();
        student.setId(123l);
        student.setName("Mohammad Abdellatif");
        student.setNumber("123456");

        PlatformTransactionManager trm = applicationContext.getBean(PlatformTransactionManager.class);
        TransactionTemplate tmpl = new TransactionTemplate(trm);

        tmpl.executeWithoutResult((s) -> {
//            em.persist(department);
            Department dept = em.merge(department);
//            Department merged = em.merge(department);
//            em.remove(merged);
            student.setDepartment(dept);
            em.persist(student);
        });
    }

}
