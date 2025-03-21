package dk.kea.studentdtostart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
//@NoArgsConstructor //tomme constructor
//@AllArgsConstructor //constructor med parametre
@Builder //hvis man har et objekt man skal initialisere og med forskellige parametre, så anvender man builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private LocalDate bornDate;

    private LocalTime bornTime;

    public Student(String name, String password, LocalDate bornDate, LocalTime bornTime) {
        this.name = name;
        this.password = password;
        this.bornDate = bornDate;
        this.bornTime = bornTime;
    }

    public Student(Long id, String name, String password, LocalDate bornDate, LocalTime bornTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.bornDate = bornDate;
        this.bornTime = bornTime;
    }


    //Tom constructor: skal anvendes af Spring JPA når der oprettes objekter
    public Student() {
    }
}