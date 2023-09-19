package ir.neshan.NavReports.mapper;

import ir.neshan.NavReports.dto.OperatorDTO;
import ir.neshan.NavReports.entities.Operator;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OperatorMapper {
    Operator toEntity(OperatorDTO operatorDTO);

    OperatorDTO toDTO(Operator operator);
}
