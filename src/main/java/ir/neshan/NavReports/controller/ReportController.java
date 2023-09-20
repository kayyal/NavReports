package ir.neshan.NavReports.controller;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.exception.UserNotFoundException;
import ir.neshan.NavReports.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @PostMapping("/add")
    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO, Authentication authentication) {
        String username = authentication.getName();
//        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        try {
            reportService.createReport(reportDTO, username);
            return ResponseEntity.status(HttpStatus.CREATED).body("report added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add report -> " + e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to find the User for this report -> " + e.getMessage());

        }
    }

    @GetMapping("/accidents/hour")
    public ResponseEntity<String> getHourWithMostAccidents() {
        String response = reportService.getHourWithMostAccidents();
        return ResponseEntity.ok(response);
    }
}
