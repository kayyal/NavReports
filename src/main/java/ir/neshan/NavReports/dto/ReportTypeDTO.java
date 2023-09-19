package ir.neshan.NavReports.dto;

import ir.neshan.NavReports.entities.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReportTypeDTO {

    private Type type;

    private String message;

    private Long duration;

}
