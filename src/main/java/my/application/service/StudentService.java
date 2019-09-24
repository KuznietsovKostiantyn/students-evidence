package my.application.service;

import my.application.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> showStudents();
    Optional<Student> getStudent(Long id);
    void deleteStudent(Long id);
    void save (Student student);
    Optional<Student> findById(Long id);
    List<Student> findAllBySurname(String surname);
}
