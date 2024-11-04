package fakestoreapi.service;

import fakestoreapi.dto.*;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class FakeStoreServiceWithJsonCore {

    private final RestTemplate restTemplate;

    /**
     * Retrieves a list of users along with their carts from the FakeStore API.
     * @return List of UsersWithCartsDTO containing users and their associated carts.
     */
    public List<UsersWithCartsDTO> getUsersWithCartsList() {

        List<UsersWithCartsDTO> usersWithCarts = new ArrayList<>();
        // Fetching user data from the FakeStore API
        ResponseEntity<String> usersResponse = restTemplate.getForEntity("https://fakestoreapi.com/users", String.class);
        // Fetching cart data from the FakeStore API
        ResponseEntity<String> cartsResponse = restTemplate.getForEntity("https://fakestoreapi.com/carts", String.class);
        // Parsing user and cart data into JSON arrays
        JSONArray usersArray = new JSONArray(usersResponse.getBody());
        JSONArray cartsArray = new JSONArray(cartsResponse.getBody());
        // Iterating through each user in the users array
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            UsersWithCartsDTO userWithCarts = new UsersWithCartsDTO();
            // Extracting user information and setting it in the UsersWithCartsDTO object
            userWithCarts.setId(user.getInt("id"));
            userWithCarts.setEmail(user.getString("email"));
            userWithCarts.setUsername(user.getString("username"));
            userWithCarts.setPassword(user.getString("password"));
            // Populate the name field in UsersWithCartsDTO
            JSONObject nameObj = user.getJSONObject("name");
            Name name = new Name();
            name.setFirstname(nameObj.getString("firstname"));
            name.setLastname(nameObj.getString("lastname"));
            userWithCarts.setName(name);
            // Populate the address field in UsersWithCartsDTO
            JSONObject addressObj = user.getJSONObject("address");
            Address address = new Address();
            address.setCity(addressObj.getString("city"));
            address.setStreet(addressObj.getString("street"));
            address.setZipcode(addressObj.getString("zipcode"));
            userWithCarts.setAddress(address);
            // Fetching carts associated with the current user
            List<CartDTO> userCarts = getUserCarts(cartsArray, userWithCarts.getId());
            userWithCarts.setCarts(userCarts);
            // Adding the user with their carts to the list
            usersWithCarts.add(userWithCarts);
        }
        return usersWithCarts;
    }

    /**
     * Retrieves carts associated with a specific user.
     * @param cartsArray JSON array containing cart data for all users.
     * @param userId ID of the user whose carts are to be retrieved.
     * @return List of CartDTO objects containing carts associated with the user.
     */
    private List<CartDTO> getUserCarts(JSONArray cartsArray, int userId) {

        List<CartDTO> userCarts = new ArrayList<>();
        // Iterating through each cart in the carts array
        for (int i = 0; i < cartsArray.length(); i++) {
            JSONObject cart = cartsArray.getJSONObject(i);
            // Checking if the cart belongs to the specified user
            if (cart.getInt("userId") == userId) {
                CartDTO cartDTO = new CartDTO();
                // Extracting cart information and setting it in the CartDTO object
                cartDTO.setId(cart.getInt("id"));
                cartDTO.setUserId(cart.getInt("userId"));
                cartDTO.setDate(cart.getString("date"));
                // Extracting products associated with the cart
                JSONArray productsArray = cart.getJSONArray("products");
                List<ProductDTO> products = new ArrayList<>();
                for (int j = 0; j < productsArray.length(); j++) {
                    JSONObject product = productsArray.getJSONObject(j);
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getInt("productId"));
                    productDTO.setQuantity(product.getInt("quantity"));
                    // Fetch product details
                    ResponseEntity<String> productResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + product.getInt("productId"), String.class);
                    JSONObject productDetails = new JSONObject(productResponse.getBody());
                    // Populate ProductDTO fields
                    productDTO.setTitle(productDetails.getString("title"));
                    productDTO.setPrice(productDetails.getDouble("price"));
                    productDTO.setCategory(productDetails.getString("category"));
                    productDTO.setDescription(productDetails.getString("description"));
                    productDTO.setImage(productDetails.getString("image"));

                    products.add(productDTO);
                }
                // Setting the products list in the CartDTO object
                cartDTO.setProducts(products);
                // Adding the cart to the list of user carts
                userCarts.add(cartDTO);
            }
        }
        return userCarts;
    }
}
