package ir.neshan.NavReports.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ReportProperties {
    @Value("${report.duration}")
    public static long DURATION;

    @Value("${report.incrementTime}")
    public static long INCREMENT_TIME;

    @Value("${report.decrementTime}")
    public static long DECREMENT_TIME;

}
