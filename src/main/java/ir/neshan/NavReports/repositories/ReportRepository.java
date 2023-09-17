package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
//    List<Report> nativeQuery(String query);
//
//    @Query(value = "SELECT r FROM Report r WHERE ST_DWithin(r.location, :route, 10) = true")
//    List<Report> findReportsNearRoute(@Param("route") LineString route);
}
