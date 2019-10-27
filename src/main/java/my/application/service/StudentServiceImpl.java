package my.application.service;

import lombok.NoArgsConstructor;
import my.application.model.Student;
import my.application.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class StudentServiceImpl implements StudentService {


    private StudentDao studentDao;

    public List<Student> showStudents(){
        return studentDao.findAll();
    }

    @Override
    public Optional<Student> getStudent(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }
    @Override
    public Optional<Student> findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAllBySurname(String surname) {
        return studentDao.findAllBySurname(surname);
    }

}
