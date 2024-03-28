package in.co.ad.springboot3.features.springboot3features.controller;

import java.util.Optional;
import java.util.Date;
import java.time.LocalDate;
import java.time.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.ad.springboot3.features.springboot3features.domain.Estudiante;
import in.co.ad.springboot3.features.springboot3features.repository.StudentRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping("/")
    public ResponseEntity<Estudiante> createCustomer(Estudiante estudiante) {
        
        LocalDate x = convertToLocalDate(estudiante.getFirstDate());
        System.err.println("Date: " + x);
        studentRepository.save(estudiante);
        //estudiante.getNotes().forEach(note -> note.setEstudiante(estudiante));
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert == null ? null : Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getCustomerById(@PathParam("id") int id) {

        Optional<Estudiante> opt = studentRepository.findById(id);
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }
}