package ir.neshan.NavReports.service;

import ir.neshan.NavReports.dto.UserDTO;
import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.User;
import ir.neshan.NavReports.mapper.UserMapper;
import ir.neshan.NavReports.repositories.ReportRepository;
import ir.neshan.NavReports.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.postgis.LineString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final UserMapper userMapper;


    public User createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        return user;
    }

    public List<Report> findReportsNearRoute(LineString route) {
        return reportRepository.findReportsNearRoute(route);
    }
}
