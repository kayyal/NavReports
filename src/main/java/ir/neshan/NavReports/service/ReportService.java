package ir.neshan.NavReports.service;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.entities.User;
import ir.neshan.NavReports.exception.DuplicateReportException;
import ir.neshan.NavReports.exception.UserNotFoundException;
import ir.neshan.NavReports.mapper.ReportMapper;
import ir.neshan.NavReports.repositories.ReportRepository;
import ir.neshan.NavReports.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private ReportMapper reportMapper;


    public ReportDTO createReport(ReportDTO reportDTO) throws UserNotFoundException {
        Report report = reportMapper.toEntity(reportDTO);
        Optional<User> userOptional = userRepository.findById(reportDTO.getUserId());
        report.setReportTime(new Date());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            report.setUser(user);
        }
        report.setType(reportDTO.getType());
        report.setMessage(reportDTO.getMessage());


        Optional<Report> existingReport = reportRepository.findByUserIdAndTypeAndLocation(
                reportDTO.getUserId(), report.getType(), report.getLocation()
        );
        if (existingReport.isPresent()) {
            // If the existing report was created within the last 2 minutes, consider it a duplicate
            if (existingReport.get().getReportTime().after(new Date(System.currentTimeMillis() - 2 * 60 * 1000))) {
                throw new DuplicateReportException("Duplicate report detected");
            }
        }

        // Convert DTO to entity
        // Set the status to UNDER_REVIEW
        report.setStatus(Status.UNDER_REVIEW);
        // Save the entity
        Report savedReport = reportRepository.save(report);
        // Convert the saved entity back to DTO
        return reportMapper.toDTO(savedReport);
    }

    @Transactional
    public void deleteRejectedReports() {
        List<Report> expiredReports = reportRepository.findAllByStatus(Status.REJECTED);
        reportRepository.deleteAll(expiredReports);
    }

    @Transactional
    public void deleteExpiredApprovedReports() {
        List<Report> approvedReports = reportRepository.findAllByStatus(Status.APPROVED);
        Date now = new Date();
        approvedReports.stream()
                .filter(report -> now.after(new Date(report.getReportTime().getTime() + report.getDuration() * 60 * 100)))
                .forEach(report -> {
                    report.setStatus(Status.EXPIRED);
                    reportRepository.save(report);
                });
    }


}
