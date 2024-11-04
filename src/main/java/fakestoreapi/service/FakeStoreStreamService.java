package fakestoreapi.service;

import fakestoreapi.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class FakeStoreStreamService {

    private final RestTemplate restTemplate;

    public List<UsersWithCartsDTO> getUsersWithCartsNew() {
        // Fetch user and cart data from the FakeStore API
        UserDTO[] usersArray = Optional.ofNullable(restTemplate.getForEntity("https://fakestoreapi.com/users", UserDTO[].class)
                .getBody()).orElse(new UserDTO[0]);

        CartDTO[] cartsArray = Optional.ofNullable(restTemplate.getForEntity("https://fakestoreapi.com/carts", CartDTO[].class)
                .getBody()).orElse(new CartDTO[0]);

        // Process each user with associated carts and products
        return Stream.of(usersArray)
                .map(user -> new UsersWithCartsDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getPassword(),
                        new Name(user.getName().getFirstname(), user.getName().getLastname()),
                        new Address(user.getAddress().getCity(), user.getAddress().getStreet(), user.getAddress().getZipcode()),
                        getUserCartsWithProducts(cartsArray, user.getId())
                ))
                .collect(Collectors.toList());
    }

    private List<CartDTO> getUserCartsWithProducts(CartDTO[] cartsArray, int userId) {
        return Stream.of(cartsArray)
                .filter(cart -> cart.getUserId() == userId)
                .peek(cart -> cart.setProducts(fetchProductDetails(cart)))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> fetchProductDetails(CartDTO cart) {
        return cart.getProducts().stream()
                .map(product -> {
                    ProductDTO productDetail = Optional.ofNullable(restTemplate.getForEntity(
                                    "https://fakestoreapi.com/products/" + product.getProductId(),
                                    ProductDTO.class)
                                    .getBody())
                            .orElse(new ProductDTO());
                    productDetail.setProductId(product.getProductId());
                    productDetail.setQuantity(product.getQuantity());
                    return productDetail;
                })
                .collect(Collectors.toList());
    }
}
