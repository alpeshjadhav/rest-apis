package productService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class CustomeProductService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<Map<String, Object>> getProductsList() {
        ResponseEntity<String> response = restTemplate.getForEntity("https://fakestoreapi.com/products", String.class);
        List<Map<String, Object>> customizedProducts = new LinkedList<>();
        try {
            JsonNode productsNode = objectMapper.readTree(response.getBody());
            for (JsonNode productNode : productsNode) {
                if (productNode != null && productNode.get("id") != null) { // Check if "id" field is present
                    Map<String, Object> customizedProduct = new HashMap<>();
                    customizedProduct.put("id", productNode.get("id").asInt());
                    customizedProduct.put("title", productNode.get("title").asText());
                    customizedProduct.put("price", productNode.get("price").asDouble());
                    customizedProduct.put("description", productNode.get("description").asText());
                    customizedProduct.put("category", productNode.get("category").asText());
                    customizedProduct.put("image", productNode.get("image").asText());

                    JsonNode ratingNode = productNode.get("rating");
                    if (ratingNode != null) { // Check if "rating" field is present
                        Map<String, Object> rating = new HashMap<>();
                        rating.put("rate", ratingNode.get("rate").asDouble());
                        rating.put("count", ratingNode.get("count").asInt());
                        customizedProduct.put("rating", rating);
                    }
                    customizedProducts.add(customizedProduct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
        return customizedProducts;
    }
}
