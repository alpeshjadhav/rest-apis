package mapper.example1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private int id;
    private int userId;
    private String date;
    private List<Product> products;

    @JsonProperty("__v")
    private int version;
}
