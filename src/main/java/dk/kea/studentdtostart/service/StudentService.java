package dk.kea.studentdtostart.service;

import dk.kea.studentdtostart.dto.StudentRequestDTO;
import dk.kea.studentdtostart.dto.StudentResponseDTO;
import dk.kea.studentdtostart.model.Student;
import dk.kea.studentdtostart.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*mellem vores forretningslogik på backend og controlleren opretter vi en omsætning/en mapping
 hvor vi mapper entitet til records*/
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /*herinde har Jarl hentet en collection og proppet det ned i en anden collection ikke helt smart af TV kokken Jarl.
    Den skal laves om så der ikke kommer students tilbage, men en liste af StudentResponse
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<Student> studentResponses = new ArrayList<>();

  // Using a for-loop to convert each Student to a StudentResponseDTO
        for (Student student : students) {
            studentResponses.add(student);
        }
        return studentResponses;
    }
*/
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = new ArrayList<>();

        // Using a for-loop to convert each Student to a StudentResponseDTO
        for (Student student : students) {
            //HER LAVES EN DTO
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                    student.getId(),
                    student.getName(),
                    student.getBornDate(),
                    student.getBornTime()
            );
        }
        return studentResponseDTOs;
    }

    public StudentResponseDTO getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student studentResponse = optionalStudent.get();

        //Vi har konverteret fra tidligere at stå return studentResponse;
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                studentResponse.getId(), studentResponse.getName(), studentResponse.getBornDate(), studentResponse.getBornTime()
        );

        return studentResponseDTO;

    }
//Vi får en DTO ind, skal pakkes ud til en student, skal gemmes og smides ind i en responseDTO
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        //Student studentResponse = studentRepository.save(studentRequest);

        //vi skal lave en ny student og tage parametre ind/samme fra studentRequestDTO
        Student newStudent = new Student(
                studentRequestDTO.name(),
                studentRequestDTO.password(),
                studentRequestDTO.bornDate(),
                studentRequestDTO.bornTime()
        );
        //vi vil gemme den nye student og den skal vi have proppet ind i en ResponseDTO og sende den tilbage.
        Student savedStudent = studentRepository.save(newStudent);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getBornDate(),
                savedStudent.getBornTime()
        );
        return studentResponseDTO;
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        student.setName(studentRequestDTO.name());
        student.setPassword(studentRequestDTO.password());
        student.setBornDate(studentRequestDTO.bornDate());
        student.setBornTime(studentRequestDTO.bornTime());

        Student studentResponse = studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                studentResponse.getId(),
                studentResponse.getName(),
                studentResponse.getBornDate(),
                studentResponse.getBornTime()
        );
        return studentResponseDTO;
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            // Throw RuntimeException if student is not found
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}
