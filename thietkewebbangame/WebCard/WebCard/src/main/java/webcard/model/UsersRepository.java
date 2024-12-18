package webcard.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Short> {

    Optional<Users> findByUsername(String username);
}
