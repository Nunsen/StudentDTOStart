package dk.kea.studentdtostart.repository;

import dk.kea.studentdtostart.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//uændret, den har vi inde på serveren

public interface StudentRepository extends JpaRepository<Student, Long> {

}
