package ir.neshan.NavReports.service;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.exception.DuplicateReportException;
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


    @Transactional
    public ReportDTO createReport(ReportDTO reportDTO) {
        // Check if a report from the same user with the same type and location already exists
        Optional<Report> existingReport = reportRepository.findByUserAndReportTypeAndLocation(
                reportDTO.getUser(), reportDTO.getReportType(), reportDTO.getLocation()
        );
        if (existingReport.isPresent()) {
            // If the existing report was created within the last 2 minutes, consider it a duplicate
            if (existingReport.get().getReportTime().after(new Date(System.currentTimeMillis() - 2 * 60 * 1000))) {
                throw new DuplicateReportException("Duplicate report detected");
            }
        }

        // Convert DTO to entity
        Report report = reportMapper.toEntity(reportDTO);
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
                .filter(report -> now.after(new Date(report.getReportTime().getTime() + report.getReportType().getDuration() * 60 * 100)))
                .forEach(report -> {
                    report.setStatus(Status.EXPIRED);
                    reportRepository.save(report);
                });
    }
}
