package fakestoreapi.service;

import fakestoreapi.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class FakeStoreWebService {

    private final WebClient webClient;

    public List<UsersWithCartsDTO> getUsersWithCartsNew1() {
        // Fetch user and cart data from the FakeStore API
        UserDTO[] usersArray = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(UserDTO[].class)
                .blockOptional()
                .orElse(new UserDTO[0]);

        CartDTO[] cartsArray = webClient.get()
                .uri("/carts")
                .retrieve()
                .bodyToMono(CartDTO[].class)
                .blockOptional()
                .orElse(new CartDTO[0]);

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
                    ProductDTO productDetail = webClient.get()
                            .uri("/products/{id}", product.getProductId())
                            .retrieve()
                            .bodyToMono(ProductDTO.class)
                            .blockOptional()
                            .orElse(new ProductDTO());
                    productDetail.setProductId(product.getProductId());
                    productDetail.setQuantity(product.getQuantity());
                    return productDetail;
                })
                .collect(Collectors.toList());
    }
}
