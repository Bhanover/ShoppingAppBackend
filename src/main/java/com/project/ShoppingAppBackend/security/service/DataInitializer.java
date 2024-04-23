package com.project.ShoppingAppBackend.security.service;

import com.project.ShoppingAppBackend.Repositories.RoleRepository;
import com.project.ShoppingAppBackend.Repositories.UserRepository;
import com.project.ShoppingAppBackend.models.RoleName;
import com.project.ShoppingAppBackend.models.User;
import jakarta.transaction.Transactional;
import com.project.ShoppingAppBackend.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("alison").isEmpty()) {
            // Create role if not exists
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_ADMIN)));

            // Create admin user
            User admin = new User();
            admin.setUsername("alison");
            admin.setPassword(passwordEncoder.encode("alisonmijaelvaleriA.1"));
            admin.setRoles(Collections.singleton(adminRole));
            userRepository.save(admin);
        }
    }

}