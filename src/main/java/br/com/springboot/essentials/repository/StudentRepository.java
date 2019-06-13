package br.com.springboot.essentials.repository;

import br.com.springboot.essentials.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murilo Victor on 20/05/2019
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    Student findByNameIgnoreCaseContaining(String name);
}
