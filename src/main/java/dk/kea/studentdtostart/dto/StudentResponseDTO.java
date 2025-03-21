package dk.kea.studentdtostart.dto;

import java.time.LocalDate;
import java.time.LocalTime;

//Fra server ud til klienten
//vi vil ikke have password med, men tage id med
public record StudentResponseDTO(Long id, String name, LocalDate bornDate, LocalTime bornTime) {
}
