package ir.neshan.NavReports.dto;

import ir.neshan.NavReports.entities.ReportType;
import ir.neshan.NavReports.entities.Status;
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

    private Long userId;

    private Status status; // it can be under-review , approved, rejected

    private Long like;

    private Date reportTime = new Date();

    private Point location;

}
