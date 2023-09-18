package ir.neshan.NavReports.service;


import ir.neshan.NavReports.repositories.OperatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperatorService {

    private final ReportService reportService;
    private final OperatorRepository operatorRepository;


}
