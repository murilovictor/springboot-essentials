package br.com.springboot.essentials.repository;

import br.com.springboot.essentials.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murilo Victor on 21/06/2019
 */
public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
