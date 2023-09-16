package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> nativeQuery(String query);
}
