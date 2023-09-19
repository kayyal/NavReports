package ir.neshan.NavReports.dto;

import ir.neshan.NavReports.entities.Type;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.io.Serializable;

@Getter
@Setter
public class ReportDTO implements Serializable {

    private Type type;

    private String message;

    private Long duration = 20L;


    private Long userId;


    private Point location;
}
