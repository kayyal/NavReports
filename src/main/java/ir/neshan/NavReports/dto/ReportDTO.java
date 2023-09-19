package ir.neshan.NavReports.dto;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.io.Serializable;

@Getter
@Setter
public class ReportDTO implements Serializable {

    private ReportTypeDTO reportTypeDTO;

    private Long userId;

    private Point location;

}
