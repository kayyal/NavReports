package ir.neshan.NavReports.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ReportType reportType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status; // it can be under-review , approved, rejected , expired

    @OneToMany
    private Set<Like> likes = new HashSet<>();//use trigger in view everytier a like anjam shod bere bishataer bekonad

    //    private Long like; // uniq for user
    //    @CreatedDate
    private Date reportTime = new Date();
    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

}

