package br.com.springboot.essentials.controller;

import br.com.springboot.essentials.model.Student;
import br.com.springboot.essentials.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Murilo Victor on 20/05/2019
 */
@RestController
@RequestMapping("students")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Student>> listAllStudents() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id) {
        Optional<Student> student = repository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        repository.deleteById(id);
    }
}




//PAROU NA AULA 9 - Iniciar aula 10