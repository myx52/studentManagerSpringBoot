package com.example.testcustomer.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner runner(StudentRepository repo) {
        return args -> {
            Student mahmud = new Student("Mahmud", LocalDate.of(2000, 8, 19), "mahmud_y@hotmail.de");
            Student martin = new Student("Martin", LocalDate.of(2001, 8, 16), "mk@gmail.com");

             repo.saveAll(List.of(martin,mahmud));
        };
    }
}
