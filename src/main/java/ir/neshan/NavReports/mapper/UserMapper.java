package ir.neshan.NavReports.mapper;


import ir.neshan.NavReports.dto.UserDTO;
import ir.neshan.NavReports.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

}
