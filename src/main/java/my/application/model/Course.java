package my.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="name")
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @Column(name="abbreviation")
    @Size(min=3, max=50)
    private String abbreviation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "course_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> students= new HashSet<>();

}
