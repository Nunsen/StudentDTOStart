package dk.kea.studentdtostart.api;

import dk.kea.studentdtostart.dto.StudentRequestDTO;
import dk.kea.studentdtostart.dto.StudentResponseDTO;
import dk.kea.studentdtostart.model.Student;
import dk.kea.studentdtostart.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        try {
            StudentResponseDTO student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Hvad skal vi have tilbage fra response? StudentResponseDTO = det vi giver tilbage
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO newStudent = studentService.createStudent(studentRequestDTO);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }


    //Vi modtager @RequestBody StudentRequestDTO og smider <StudentResponseDTO> tilbage
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        try {
            StudentResponseDTO updatedStudent = studentService.updateStudent(id, studentRequestDTO);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Returnerer tom status med No-content hvis den findes, og kan den ikke finde den via id returnerer den status Not found.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
