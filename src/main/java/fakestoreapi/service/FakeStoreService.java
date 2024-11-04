package fakestoreapi.service;

import fakestoreapi.dto.*;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class FakeStoreService {

    private final RestTemplate restTemplate;

    /**
     * Retrieves a list of users along with their carts from the FakeStore API.
     * @return List of UsersWithCartsDTO containing users and their associated carts.
     */
    public List<UsersWithCartsDTO> getUsersWithCarts() {
        // Initialize a list to hold the users with their carts
        List<UsersWithCartsDTO> usersWithCarts = new ArrayList<>();

        // Fetch user data from the FakeStore API
        ResponseEntity<UserDTO[]> usersResponse = restTemplate.getForEntity("https://fakestoreapi.com/users", UserDTO[].class);
        // Fetch cart data from the FakeStore API
        ResponseEntity<CartDTO[]> cartsResponse = restTemplate.getForEntity("https://fakestoreapi.com/carts", CartDTO[].class);

        // Extract user and cart arrays from the responses
        UserDTO[] usersArray = usersResponse.getBody();
        CartDTO[] cartsArray = cartsResponse.getBody();

        // Check if both user and cart arrays are not null
        if (usersArray != null && cartsArray != null) {
            // Iterate through each user
            for (UserDTO user : usersArray) {
                // Create a DTO object to hold user information along with their carts
                UsersWithCartsDTO userWithCarts = new UsersWithCartsDTO();
                // Set basic user information
                userWithCarts.setId(user.getId());
                userWithCarts.setEmail(user.getEmail());
                userWithCarts.setUsername(user.getUsername());
                userWithCarts.setPassword(user.getPassword());
                // Populate the name field in UsersWithCarts
                Name name = new Name();
                name.setFirstname(user.getName().getFirstname());
                name.setLastname(user.getName().getLastname());
                userWithCarts.setName(name);
                // Populate the address field in UsersWithCarts
                Address address = new Address();
                address.setCity(user.getAddress().getCity());
                address.setStreet(user.getAddress().getStreet());
                address.setZipcode(user.getAddress().getZipcode());
                userWithCarts.setAddress(address);
                // Get carts associated with the current user
                List<CartDTO> userCarts = getUserCarts(cartsArray, user.getId());
                // Fetch product details for each product in the cart
                for (CartDTO cart : userCarts) {
                    List<ProductDTO> products = new ArrayList<>();
                    for (ProductDTO product : cart.getProducts()) {
                        // Fetch product details
                        ResponseEntity<ProductDTO> productResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + product.getProductId(), ProductDTO.class);
                        ProductDTO productDetail = productResponse.getBody();
                        // Add fetched product details to the list
                        if (productDetail != null) {
                            productDetail.setProductId(product.getProductId());
                            productDetail.setQuantity(product.getQuantity());
                        }
                        products.add(productDetail);
                    }
                    // Set the updated list of products in the cart
                    cart.setProducts(products);
                }
                // Set the carts for the user
                userWithCarts.setCarts(userCarts);
                // Add the user with their carts to the list
                usersWithCarts.add(userWithCarts);
            }
        }
        // Return the list of users with their carts
        return usersWithCarts;
    }

    /**
     * Retrieves carts associated with a specific user.
     * @param cartsArray Array of CartDTO containing carts for all users.
     * @param userId ID of the user whose carts are to be retrieved.
     * @return List of CartDTO objects containing carts associated with the user.
     */
    private List<CartDTO> getUserCarts(CartDTO[] cartsArray, int userId) {
        // Initialize a list to hold the carts associated with the user
        List<CartDTO> userCarts = new ArrayList<>();
        // Iterate through each cart
        for (CartDTO cart : cartsArray) {
            // Check if the cart belongs to the specified user
            if (cart.getUserId() == userId) {
                // Add the cart to the list of user carts
                userCarts.add(cart);
            }
        }
        // Return the list of carts associated with the user
        return userCarts;
    }
}
