package br.com.springboot.essentials.controller;

import br.com.springboot.essentials.error.ResourceNotFoundException;
import br.com.springboot.essentials.model.Student;
import br.com.springboot.essentials.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Murilo Victor on 20/05/2019
 */
@RestController
@RequestMapping("v1/")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoint to manage Student")
@ApiOperation(value = "Management Student API")
public class StudentController {

    private final StudentRepository repository;

    @ApiOperation(value = "List all Student with Pageable", response = Student[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("protected/students")
    public ResponseEntity<Iterable<Student>> listAllStudents(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Student by id", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        verifyIfStudentExists(id);
        Optional<Student> student = repository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Student by name", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "protected/students/findByName/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Student by name with Pageable", response = Student[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "protected/students/findByNameWithPageable/{name}")
    public ResponseEntity<List<Student>> getStudentByNameWithPageable(@PathVariable String name, Pageable pageable){
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Create Student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("admin/students")
    public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("admin/students")
    public ResponseEntity<Student> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Student By id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted student"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("admin/students/{id}")
    public void delete (@PathVariable Long id){
        verifyIfStudentExists(id);
        repository.deleteById(id);
    }

    private void verifyIfStudentExists(Long id){
        if (!repository.findById(id).isPresent())
            throw new ResourceNotFoundException("Student Not Found for ID: " + id);
    }
}