package ir.neshan.NavReports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NavReportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavReportsApplication.class, args);
    }

}
