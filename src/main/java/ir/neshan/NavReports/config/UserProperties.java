package ir.neshan.NavReports.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class UserProperties {

    @Value("${user.userRole}")
    public static String USER_ROLE;

    @Value("${user.operatorRole}")
    public static String OPERATOR_ROLE;

}
