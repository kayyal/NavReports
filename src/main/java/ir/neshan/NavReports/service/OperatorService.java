package ir.neshan.NavReports.service;


import ir.neshan.NavReports.entities.Operator;
import ir.neshan.NavReports.exception.OperatorNotFoundException;
import ir.neshan.NavReports.repositories.OperatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public Operator findByOperatorName(String name) throws OperatorNotFoundException {
        return operatorRepository.findByName(name)
                .orElseThrow(() -> new OperatorNotFoundException("Operator not found"));
    }

}
