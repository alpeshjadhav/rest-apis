package mapper.example2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;
    private Address address;

    @JsonProperty("__v")
    private int version;
}
