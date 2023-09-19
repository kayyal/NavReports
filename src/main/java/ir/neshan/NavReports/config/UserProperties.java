package ir.neshan.NavReports.config;

import ir.neshan.NavReports.entities.Operator;
import ir.neshan.NavReports.entities.User;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "users")
@Getter
@Configuration
public class UserProperties {
    private final List<User> userList = new ArrayList<>();
    private final List<Operator> operatorList = new ArrayList<>();

}
