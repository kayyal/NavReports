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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    @Transactional
    public void likeDislikeReport(Long userId, Long reportId, boolean isLike) throws ReportNotFoundException {
        Optional<User> user = userRepository.findById(userId); // method to get a User by its id
        Optional<Report> report = reportRepository.findById(reportId); // method to get a Report by its id
        if (user.isEmpty() || report.isEmpty()) throw new ReportNotFoundException("the report is not existed");

        if (isLike) {
            Like like = new Like();
            like.setUser(user.get());
            like.setReport(report.get());
            report.get().getLikesUsers().add(like);
            report.get().setDuration(report.get().getDuration() + 2);
        } else {
            Like like = report.get().getLikesUsers().stream()
                    .filter(likeee -> likeee.getUser().getId().equals(userId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User has not liked this report"));

            report.get().getLikesUsers().remove(like);
            report.get().setDuration(report.get().getDuration() - 2);
        }

        reportRepository.save(report.get());
    }
}
