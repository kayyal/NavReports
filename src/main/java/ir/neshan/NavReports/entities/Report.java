package ir.neshan.NavReports.entities;

import jakarta.persistence.*;
import lombok.*;
import org.postgis.Point;
import org.springframework.data.annotation.CreatedDate;

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

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Operator operator;

    @Enumerated(EnumType.STRING)
    private String status; // it can be under-review , approved, rejected

    private Integer like;

    private Integer dislike;

    @CreatedDate
    private Date reportTime = new Date();

    private Point location;

    private boolean isValidated = false;

}

