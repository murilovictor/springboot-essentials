package br.com.springboot.essentials.repository;

import br.com.springboot.essentials.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Murilo Victor on 20/05/2019
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByNameIgnoreCaseContaining(String name);
    List<Student> findByNameIgnoreCaseContaining(String name, Pageable pageable);

}
