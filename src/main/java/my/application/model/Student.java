package my.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="student")
@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="name")
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @Column(name="surname")
    @Size(min=3, max=50)
    private String surname;

    @NotNull
    @Column(name="age")
    @Digits(integer = 2, fraction = 0)
    private int age;

    @NotNull
    @Column(name="personal_number", unique = true)
    private String personalNumber;

    public Student(String name, String surname, int age, String personalNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.personalNumber = personalNumber;
    }
}
