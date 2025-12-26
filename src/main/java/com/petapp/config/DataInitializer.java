package com.petapp.config;

import com.petapp.model.User;
import com.petapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Создаем администратора, если его еще нет
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.addRole("ROLE_ADMIN");
            userRepository.save(admin);
            
            System.out.println("===========================================");
            System.out.println("✅ Создан администратор по умолчанию:");
            System.out.println("   Логин: admin");
            System.out.println("   Пароль: admin123");
            System.out.println("===========================================");
        }

        // Создаем обычного пользователя для теста, если его еще нет
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.addRole("ROLE_USER");
            userRepository.save(user);
            
            System.out.println("===========================================");
            System.out.println("✅ Создан тестовый пользователь:");
            System.out.println("   Логин: user");
            System.out.println("   Пароль: user123");
            System.out.println("===========================================");
        }
    }
}