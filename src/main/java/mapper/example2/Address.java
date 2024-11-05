package mapper.example2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private Geolocation geolocation;
    private String city;
    private String street;
    private int number;
    private String zipcode;
}
