package com.example.springbootbasicauthenticationswagger.util;

import com.example.springbootbasicauthenticationswagger.model.Role;
import com.example.springbootbasicauthenticationswagger.model.User;
import com.example.springbootbasicauthenticationswagger.repository.RoleRepository;
import com.example.springbootbasicauthenticationswagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoad implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role roleUser = Role.builder()
                .name("USER")
                .build();

        Role roleAdmin = Role.builder()
                .name("ADMIN")
                .build();



        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        List<Role> userRoleList = new ArrayList<>();
        userRoleList.add(roleUser);

        User user = User.builder()
                .username("Raşit")
                .password(new BCryptPasswordEncoder().encode("1111"))
                .roles(userRoleList)
                .build();

        userRepository.save(user);

        List<Role> adminRoleList = new ArrayList<>();
        adminRoleList.add(roleAdmin);

        User admin = User.builder()
                .username("Ömer")
                .password(new BCryptPasswordEncoder().encode("2222"))
                .roles(adminRoleList)
                .build();
        userRepository.save(admin);


        System.out.println("-----Role-----");
        roleRepository.findAll().forEach(System.out::println);
        System.out.println("-----User-----");
        userRepository.findAll().forEach(System.out::println);



    }
}
