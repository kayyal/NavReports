package ir.neshan.NavReports.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    public static final String USER = "USER";
    public static final String OPERATOR = "OPERATOR";
    private final UserProperties userProperties;
    private final OperatorProperties operatorProperties;

    @Bean
    public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {

        System.out.println("userProperties.getUsers() = " + userProperties.getUserList());
        System.out.println("operatorProperties.getOperators() = " + operatorProperties.getOperatorList());

        List<UserDetails> userDetailsList1 = userProperties.getUserList()
                .stream()
                .map(user -> User.withUsername(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .roles(USER)
                        .build())
                .collect(Collectors.toList());
        List<UserDetails> operator1 = operatorProperties.getOperatorList().stream()
                .map(operator -> User.withUsername(operator.getUsername())
                        .password(passwordEncoder.encode(operator.getPassword()))
                        .roles(OPERATOR)
                        .build()
                ).collect(Collectors.toList());

        userDetailsList1.addAll(operator1);


        return new InMemoryUserDetailsManager(userDetailsList1);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeRequests()
                .requestMatchers("/operator/**").hasRole(OPERATOR)
                .requestMatchers("/report/**", "/api/**").hasRole(USER)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
