package ir.neshan.NavReports.controller;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.exception.UserNotFoundException;
import ir.neshan.NavReports.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @PostMapping("/add")
    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO) {
        try {
            reportService.createReport(reportDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("report added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add report -> " + e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to find the User for this report -> " + e.getMessage());

        }
    }
}
