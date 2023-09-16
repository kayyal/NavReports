package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
