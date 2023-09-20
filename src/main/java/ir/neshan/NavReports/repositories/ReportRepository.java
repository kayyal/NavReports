package ir.neshan.NavReports.repositories;

import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.entities.Type;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByUserIdAndTypeAndLocation(Long userId, Type type, Point location);

    List<Report> findByUserId(Long userId);

    @Query(value = "SELECT r FROM Report r WHERE ST_DWithin(r.location, :route, 100 , false ) = true")
    List<Report> findReportsNearRoute(@Param("route") LineString route);

    @Query(value = "SELECT HOUR(r.reportTime) AS hour, COUNT(r) AS count FROM Report r WHERE r.type = :type GROUP BY HOUR(r.reportTime) ORDER BY COUNT(r) DESC")
    List<Object[]> findAccidentCountByHour(@Param("type") Type type);

    List<Report> findAllByStatus(Status status);
}
