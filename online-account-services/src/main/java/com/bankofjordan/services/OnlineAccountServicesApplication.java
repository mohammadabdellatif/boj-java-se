package com.bankofjordan.services;

import com.bankofjordan.services.entities.CustomerEntity;
import com.bankofjordan.services.entities.CustomerEntityRepository;
import com.bankofjordan.services.entities.examples.Course;
import com.bankofjordan.services.entities.examples.Department;
import com.bankofjordan.services.entities.examples.Student;
import com.bankofjordan.services.entities.examples.StudentRepository;
import com.bankofjordan.services.open.*;
import com.bankofjordan.services.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
public class OnlineAccountServicesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OnlineAccountServicesApplication.class, args);


//        OpenQuickAccountHandler openQuickAccountHandler = applicationContext.getBean(OpenQuickAccountHandler.class);
//
//
//        System.out.println("current thread: " + Thread.currentThread().getName());
//        Thread thread = new Thread(() -> {
//            System.out.println("current thread: " + Thread.currentThread().getName());
//            OpenQuickAccountOutput output = openQuickAccountHandler.open(createInput());
//            System.out.println(output.getCif());
//            System.out.println(output.getIban());
//        });
//
//        thread.start();// it will start the process later, triggering for a thread to start

//        System.out.println("done");
    }

    private static OpenQuickAccountInput createInput() {
        Birth birth = new Birth(LocalDate.of(2005, 10, 17), "KW");
        ResidenceAddress residenceAddress = new ResidenceAddress("Khalifa Street", "Al-Mansoura", "Jordan", "Jordan", "12345");
        ContactInfo contactInfo = new ContactInfo("0799409461", "mohammad.s.abdellatif@gmail.com");
        WealthSource wealthSource = new WealthSource(BigDecimal.valueOf(1000), Currency.getInstance("JOD"), "salary");
        OpenQuickAccountInput input = new OpenQuickAccountInput("9851016141",
                "NID",
                "mohammad shawkat abdullah abdellatif",
                "male",
                "JOR",
                birth,
                residenceAddress,
                contactInfo,
                wealthSource);
        return input;
    }

    private static void persistEntity(ConfigurableApplicationContext applicationContext) {
        CustomerEntityRepository repository = applicationContext.getBean(CustomerEntityRepository.class);

        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId("9851016621");
        customer.setCustomerIdType("NID");
        customer.setCustomerName("Mohammad Abdellatif");
        customer.setCif("789654");
        customer.setCity("Amman");
        customer.setCountry("Jordan");
        customer.setGender(CustomerEntity.Gender.MALE);
        customer.setAmount(BigDecimal.valueOf(200));
        customer.setCountryOfBirth("KW");
        customer.setDateOfBirth(LocalDate.now());
        customer.setCurrency("JOD");
        customer.setIncomeType("SALARY");
        customer.setNationality("Jordanian");
        customer.setStreet("Amman");

        repository.save(customer);
    }

    private static void springData2(ConfigurableApplicationContext applicationContext) {
        StudentRepository studentRepository = applicationContext.getBean(StudentRepository.class);

        List<Student> byCoursesName = studentRepository.findByCoursesName("Statistics 101");
        for (Student student : byCoursesName) {
            System.out.println(student.getName());
        }
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
