package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.login = :login")
    User findByLogin(String login);
}
