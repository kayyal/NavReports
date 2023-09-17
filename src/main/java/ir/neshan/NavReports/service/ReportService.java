package ir.neshan.NavReports.service;

import ir.neshan.NavReports.entities.Report;
import ir.neshan.NavReports.repositories.OperatorRepository;
import ir.neshan.NavReports.repositories.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {
    ReportRepository reportRepository;
    OperatorRepository operatorRepository;

    public Report addReport(Report report, Long operatorId) {
//        // Check if operator exists and is authorized
//        Operator operator = operatorRepository.findById(operatorId)
//                .orElseThrow(() -> new RuntimeException("Operator not found"));
//
//        // If operator is not authorized, throw an exception
////        if (!operator.isAuthorized()) {
////            throw new RuntimeException("Operator is not authorized to approve reports");
////        }
//
//        // Check if report already exist within the radius and timeframe
////        String query = "SELECT * FROM reports WHERE ST_DWithin(geometry, ST_GeomFromText('POINT(" + report.getLongitude() + " " + report.getLatitude() + ")',4326), 0.0001) AND report_time > NOW() - INTERVAL '5 MINUTE' AND report_type_id = " + report.getReportType().getId();
////        List<Report> reportList = reportRepository.nativeQuery(query);
//
////        if (reportList.size() > 0) {
////            throw new RuntimeException("Duplicate report");
////        }
//
//        report.setReportTime(new Date(97897));
//        report.setOperator(operator);
//
//        return reportRepository.save(report);
        return new Report();
    }
}
