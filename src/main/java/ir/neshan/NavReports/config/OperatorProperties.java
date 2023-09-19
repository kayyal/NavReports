package ir.neshan.NavReports.config;


import ir.neshan.NavReports.entities.Operator;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "operators")
@Getter
@Configuration
public class OperatorProperties {
    private final List<Operator> operatorList = new ArrayList<>();

}
