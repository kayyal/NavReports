package ir.neshan.NavReports.dto;

import ir.neshan.NavReports.entities.ReportType;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.postgis.Point;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {


    private ReportType reportType;

    private User user;

    private Status status; // it can be under-review , approved, rejected

    private Integer like;

    private Date reportTime = new Date();

    private Point location;

    private boolean isActivated = false;
}
