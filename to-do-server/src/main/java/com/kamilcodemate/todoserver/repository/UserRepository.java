package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     /** Getting User by username
      * @param username Username of a user to get
      * @return User with provided username
      */
     User getUserByUsername(String username);
}
