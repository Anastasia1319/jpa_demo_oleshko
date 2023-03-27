package org.belhard.hibernate;

import org.belhard.hibernate.dao.*;
import org.belhard.hibernate.dao.impl.*;
import org.belhard.hibernate.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static UserRepository userRepository;
    private static DepartmentRepository departmentRepository;
    private static EmployeeRepository employeeRepository;
    private static SectionRepository sectionRepository;
    private static StudentRepository studentRepository;

    public static void main(String[] args) {
        init();
        try {
            //find
//            User user = manager.find(User.class, 13L);
//            System.out.println(user.getLogin());
//            Department department = manager.find(Department.class, 13L);
//            System.out.println(department);
//            Employee employee = manager.find(Employee.class, 999L);
//            System.out.println(employee);
//            Section section = manager.find(Section.class, 33L);
//            System.out.println(section);
//            Student student = manager.find(Student.class, 333L);
//            System.out.println(student);


//            userRepository = new UserRepositoryImpl(manager);
//            departmentRepository = new DepartmentRepositoryImpl(manager);
//            employeeRepository = new EmployeeRepositoryImpl(manager);
//            sectionRepository = new SectionRepositoryImpl(manager);
            studentRepository = new StudentRepositoryImpl(manager);

            //find all
//            List<User> users = userRepository.findAll();
//            System.out.println(users);

//            List<Department> departments = departmentRepository.findAll();
//            System.out.println(departments);

//            List<Employee> employees = employeeRepository.findAll();
//            System.out.println(employees);

//            List<Section> sections = sectionRepository.findAll();
//            System.out.println(sections);

//            List<Student> students = studentRepository.findAll();
//            System.out.println(students);

            //save
//            PersonalInfo userInfo = new PersonalInfo();
//            userInfo.setEmail("dan@gmail.com");
//            userInfo.setFirstName("Dan");
//            userInfo.setLastName("Suq");
//            userInfo.setPhoneNumber("+8256415979");
//            User newUser = new User();
//            newUser.setLogin("Dan");
//            newUser.setPassword("123456789");
//            newUser.setPersonalInfo(userInfo);
//            userRepository.save(newUser);

            //delete
//            boolean deleted = userRepository.delete(1001L);
//            System.out.println("Deleted: " + deleted);
        } finally {
            down();
        }
    }

    private static void init() {
        factory = Persistence.createEntityManagerFactory("remote");
        manager = factory.createEntityManager();
    }

    private static void down() {
        if (factory != null) {
            factory.close();
        }
    }
}
