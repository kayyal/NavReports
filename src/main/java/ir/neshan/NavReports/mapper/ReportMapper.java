package ir.neshan.NavReports.mapper;


import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.entities.Report;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReportMapper {

    Report toEntity(ReportDTO reportDTO);

    ReportDTO toDTO(Report report);
}
