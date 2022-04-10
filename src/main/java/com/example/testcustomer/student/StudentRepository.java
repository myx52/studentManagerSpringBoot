package com.example.testcustomer.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select s From Student s where s.email =?1") //JPQL and not SQL. Student is our Student class which we created
    Optional<Student> findStudentByEmail(String email);


}
