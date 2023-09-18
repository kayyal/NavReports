package ir.neshan.NavReports.entities;

import jakarta.persistence.*;
import lombok.*;
import org.postgis.Point;

import java.util.Date;

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

    @Column(name = "likes")
    private Long like;

    //    @CreatedDate
    private Date reportTime = new Date();

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point location;

    private boolean isActivated = false;
}

