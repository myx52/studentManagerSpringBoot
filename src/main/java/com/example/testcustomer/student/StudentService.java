package com.example.testcustomer.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /*public List<Student> getStudent() {
        return List.of(new Student("Mahmud", 21, LocalDate.of(2000, 8, 19), "mahmud_y@hotmail.de"),
                new Student("Martin", 21, LocalDate.of(2001, 8, 16), "mk@gmail.com"));
    }*/

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email wird schon benutzt");
        }
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            new IllegalStateException("Student with Id " + studentId + " existiert nicht.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateNameStudent(Long studentId, String name) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()){
            throw new IllegalStateException("Student doenst exist");
        }
        if(studentOptional.get().getName().equals(name)){
            throw new IllegalStateException("The student has already this name");
        }
        studentOptional.get().setName(name);
    }
}
