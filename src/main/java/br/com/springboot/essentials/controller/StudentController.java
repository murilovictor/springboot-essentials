package br.com.springboot.essentials.controller;

import br.com.springboot.essentials.error.ResourceNotFoundException;
import br.com.springboot.essentials.model.Student;
import br.com.springboot.essentials.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Iterable<Student>> listAllStudents(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);

        verifyIfStudentExists(id);
        Optional<Student> student = repository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        verifyIfStudentExists(id);
        repository.deleteById(id);
    }

    private void verifyIfStudentExists(Long id){
        if (!repository.findById(id).isPresent())
            throw new ResourceNotFoundException("Student Not Found for ID: " + id);
    }
}