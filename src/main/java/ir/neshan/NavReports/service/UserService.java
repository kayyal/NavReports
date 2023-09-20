package ir.neshan.NavReports.service;

import ir.neshan.NavReports.dto.UserDTO;
import ir.neshan.NavReports.entities.Like;
import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.User;
import ir.neshan.NavReports.exception.ReportNotFoundException;
import ir.neshan.NavReports.mapper.UserMapper;
import ir.neshan.NavReports.repositories.ReportRepository;
import ir.neshan.NavReports.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.postgis.LineString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public User createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user;
    }

    public List<Report> findReportsNearRoute(LineString route) {
        return reportRepository.findReportsNearRoute(route);
    }


    @Transactional
    public void likeDislikeReport(String username, Long reportId, boolean isLike) throws ReportNotFoundException {
        User user = userRepository.findByName(username); // method to get a User by its id
        Optional<Report> report = reportRepository.findById(reportId); // method to get a Report by its id
        if (user == null || report.isEmpty()) throw new ReportNotFoundException("the report is not existed");

        if (isLike) {
            Like like = new Like();
            like.setUser(user);
            like.setReport(report.get());
            report.get().getLikesUsers().add(like);
            report.get().setDuration(report.get().getDuration() + 2);
        } else {
            Like like = report.get().getLikesUsers().stream()
                    .filter(l -> l.getUser().getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User has not liked this report"));

            report.get().getLikesUsers().remove(like);
            report.get().setDuration(report.get().getDuration() - 2);
        }

        reportRepository.save(report.get());
    }
}
