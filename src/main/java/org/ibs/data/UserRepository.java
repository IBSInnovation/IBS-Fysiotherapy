package org.ibs.data;

import org.ibs.domain.Category;
import org.ibs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*Optional<User> findByAuth0(String sub);*/

}
