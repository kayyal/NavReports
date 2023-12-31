package ir.neshan.NavReports.service;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.entities.Status;
import ir.neshan.NavReports.entities.Type;
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


    public ReportDTO createReport(ReportDTO reportDTO, String name) throws UserNotFoundException {
        Report report = reportMapper.toEntity(reportDTO);
        setUser(report, name);
        setReportTime(report);
        checkForDuplicateReport(reportDTO);
        setStatus(report);
        return saveReport(report);
    }

    private void setUser(Report report, String name) throws UserNotFoundException {
        User user = userRepository.findByName(name);
        if (user != null) {
            report.setUser(user);
        } else {
            throw new UserNotFoundException("there is no user with id you asked !");
        }
    }

    private void setReportTime(Report report) {
        report.setReportTime(new Date());
        report.setDuration(30 * 60 * 1000L);
    }


    private void checkForDuplicateReport(ReportDTO reportDTO) {
        Optional<Report> existingReport = reportRepository.findByUserIdAndTypeAndLocation(
                reportDTO.getUserId(), reportDTO.getType(), reportDTO.getLocation()
        );
        if (existingReport.isPresent()) {
            // If the existing report was created within the last 2 minutes, consider it a duplicate
            if (existingReport.get().getReportTime().after(new Date(System.currentTimeMillis() - 2 * 60 * 1000))) {
                throw new DuplicateReportException("Duplicate report detected");
            }
        }
    }

    private void setStatus(Report report) {
        report.setStatus(Status.UNDER_REVIEW);
    }

    private ReportDTO saveReport(Report report) {
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

    public String getHourWithMostAccidents() {
        List<Object[]> result = reportRepository.findAccidentCountByHour(Type.ACCIDENT);

        if (result.isEmpty()) {
            return "No accidents found.";
        }

        Integer hour = (Integer) result.get(0)[0];
        Long count = (Long) result.get(0)[1];

        return "Hour with the most accidents: " + hour + ", Number of accidents: " + count;
    }


}
