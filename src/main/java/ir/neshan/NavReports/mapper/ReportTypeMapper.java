package ir.neshan.NavReports.mapper;

import ir.neshan.NavReports.dto.ReportTypeDTO;
import ir.neshan.NavReports.entities.ReportType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReportTypeMapper {

    ReportType toEntity(ReportTypeDTO reportTypeDTO);

    ReportTypeDTO toDTO(ReportType reportType);
}
