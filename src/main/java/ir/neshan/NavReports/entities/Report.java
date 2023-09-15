package ir.neshan.NavReports.entities;

import jakarta.persistence.*;
import org.postgis.Point;

import java.sql.Time;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    ReportType reportType;

    @OneToOne
    User user;

    @OneToOne
    Operator operator;

    String status; // it can be under-review , approved, rejected

    Integer like;

    Integer dislike;

    Time reportTime;

    Point location;

}
