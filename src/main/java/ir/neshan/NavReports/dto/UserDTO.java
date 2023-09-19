package ir.neshan.NavReports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")

    private String email;

    @JsonProperty("password")

    private String password;
}
