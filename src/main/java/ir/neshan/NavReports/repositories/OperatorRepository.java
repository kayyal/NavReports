package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Optional<Operator> findByName(String name);
}
