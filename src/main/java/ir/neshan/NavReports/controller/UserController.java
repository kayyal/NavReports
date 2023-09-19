package ir.neshan.NavReports.controller;

import ir.neshan.NavReports.dto.ReportDTO;
import ir.neshan.NavReports.dto.UserDTO;
import ir.neshan.NavReports.exception.UserNotFoundException;
import ir.neshan.NavReports.mapper.ReportMapper;
import ir.neshan.NavReports.service.ReportService;
import ir.neshan.NavReports.service.UserService;
import lombok.AllArgsConstructor;
import org.postgis.LineString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final ReportService reportService;
    private final UserService userService;
    private final ReportMapper reportMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }


    @PostMapping("/create/report")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) throws UserNotFoundException {
        ReportDTO reportSummaryDTO = reportService.createReport(reportDTO);
        return new ResponseEntity<>(reportSummaryDTO, HttpStatus.CREATED);
    }


    @GetMapping("/route")
    public List<ReportDTO> findReportsNearRoute(@RequestBody LineString route) {
        return userService.findReportsNearRoute(route)
                .stream()
                .map(report -> reportMapper.toDTO(report))
                .collect(Collectors.toList());
    }
}
