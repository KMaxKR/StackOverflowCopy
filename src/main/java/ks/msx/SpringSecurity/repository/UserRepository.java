package ks.msx.SpringSecurity.repository;

import ks.msx.SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT u FROM User u WHERE u.username = :username"
    )
    Optional<User> findUserByUsername(@Param("username") String username);


//    User findUserByUsername(@Param("username") String username);
}
