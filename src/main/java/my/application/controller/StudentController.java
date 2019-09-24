package my.application.controller;

import my.application.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import my.application.service.StudentService;

@Controller
public class StudentController {


    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/")
    public String showStudents(Model model){
        model.addAttribute("students", studentService.showStudents());
        if (!model.containsAttribute("student"))
            model.addAttribute("student", new Student("", "", 0, ""));
        return "index";
    }
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        return "redirect:/";
    }
    @GetMapping("/editStudent")
    public String editStudent(@RequestParam Long id, Model model){
        model.addAttribute("student", studentService.findById(id));
        return showStudents(model);
    }
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return "redirect:/";
    }

}
