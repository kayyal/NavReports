package ir.neshan.NavReports.controller;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.dto.UserDTO;
import ir.neshan.NavReports.service.ReportService;
import ir.neshan.NavReports.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final ReportService reportService;
    private final UserService userService;

    @PostMapping("/create/report")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
        ReportDTO reportSummaryDTO = reportService.createReport(reportDTO);
        return new ResponseEntity<>(reportSummaryDTO, HttpStatus.CREATED);
    }

    @PostMapping("/create/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
