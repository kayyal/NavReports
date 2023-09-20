package ir.neshan.NavReports.entities;

import ir.neshan.NavReports.config.SecurityConfig;
import ir.neshan.NavReports.exception.ReportNotFoundException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Report> reports;

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void removeReport(Report report) throws ReportNotFoundException {
        if (this.reports.contains(report)) {
            this.reports.remove(report);
        } else {
            throw new ReportNotFoundException("the report is not in the user's reports !");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(SecurityConfig.USER));
    }

    @Override
    public String getUsername() {
        return this.name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
