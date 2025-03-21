package dk.kea.studentdtostart.dto;

import java.time.LocalDate;
import java.time.LocalTime;

//En forespørgsel tages i mod af os
public record StudentRequestDTO(String name, String password, LocalDate bornDate, LocalTime bornTime) {
}

