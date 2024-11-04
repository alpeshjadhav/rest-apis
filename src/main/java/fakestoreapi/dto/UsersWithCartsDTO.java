package fakestoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersWithCartsDTO {
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private Address address;
    private List<CartDTO> carts;
}
