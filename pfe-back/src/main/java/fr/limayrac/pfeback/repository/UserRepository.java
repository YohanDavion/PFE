package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email = ?1")
    User findByEmail(String email);
}
