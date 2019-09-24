package my.application.controller;

import my.application.model.Student;
import my.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public Iterable<Student> showStudents(){ return studentService.showStudents(); }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity addStudent (@RequestBody Student student){
        studentService.save(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findBySurname/{surname}")
    public List<Student> findBySurname(@PathVariable String surname){
        return studentService.findAllBySurname(surname);
    }

}
