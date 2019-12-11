package be.ehb.demo.model;

import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,Integer> {
}
