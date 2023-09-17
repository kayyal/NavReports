package ir.neshan.NavReports.service;

import ir.neshan.NavReports.repositories.ReportRepository;
import ir.neshan.NavReports.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;


}
