package org.belhard.hibernate;

import org.belhard.hibernate.dao.UserRepository;
import org.belhard.hibernate.dao.impl.UserRepositoryImpl;
import org.belhard.hibernate.entity.PersonalInfo;
import org.belhard.hibernate.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static UserRepository userRepository;

    public static void main(String[] args) {
        init();
        try {
            //find
//            User user = manager.find(User.class, 13L);
//            System.out.println(user.getLogin());

//            userRepository = new UserRepositoryImpl(manager);

            //find all
//            List<User> users = userRepository.findAll();
//            System.out.println(users);

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
