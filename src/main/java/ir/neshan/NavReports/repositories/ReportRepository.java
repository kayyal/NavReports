package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.ReportType;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.entities.User;
import org.postgis.LineString;
import org.postgis.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByUserAndReportTypeAndLocation(User user, ReportType reportType, Point location);
//    List<Report> nativeQuery(String query);

    @Query(value = "SELECT r FROM Report r WHERE ST_DWithin(r.location, :route, 10) = true")
    List<Report> findReportsNearRoute(@Param("route") LineString route);

    List<Report> findAllByStatus(Status status);
}
