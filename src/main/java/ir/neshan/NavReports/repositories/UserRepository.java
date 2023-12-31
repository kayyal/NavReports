package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndPassword(String name, String password);


}
