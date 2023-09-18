package ir.neshan.NavReports.entities;

import ir.neshan.NavReports.exception.ReportNotFoundException;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Report> reports;

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void removeReport(Report report) throws ReportNotFoundException {
        if (this.reports.contains(report)) {
            this.reports.remove(report);
        } else {
            throw new ReportNotFoundException("the report is not in the user's reports !");
        }
    }
}
