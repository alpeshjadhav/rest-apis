package productService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private static final String API_URL = "https://dummyjson.com/products";

    public String getProducts() throws IOException {
        //TODO: get product list with all field
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JsonNode jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode productsNode = jsonNode.get("products");

            if (productsNode != null && productsNode.isArray()) {
                List<Map<String, Object>> productList =
                        mapper.readValue(productsNode.toString(),
                                mapper.getTypeFactory().constructCollectionType(List.class, LinkedHashMap.class));

                return mapper.writeValueAsString(productList);
            } else {
                throw new IOException("Unable to find 'products' array in the JSON response");
            }
        } else {
            throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
        }
    }

    public String getAllProducts() throws IOException {
        //TODO: get product list with selected fields
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JsonNode jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode productsNode = jsonNode.get("products");

            if (productsNode != null && productsNode.isArray()) {
                List<Map<String, Object>> productList = new ArrayList<>();
                for (JsonNode product : productsNode) {
                    Map<String, Object> productMap = createProductMap(product);
                    productList.add(productMap);
                }
                return mapper.writeValueAsString(productList);
            } else {
                throw new IOException("Unable to find 'products' array in the JSON response");
            }
        } else {
            throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
        }
    }

    private Map<String, Object> createProductMap(JsonNode product) {
        Map<String, Object> productMap = new LinkedHashMap<>();
        productMap.put("id", product.get("id").asText());
        productMap.put("title", product.get("title").asText());
        productMap.put("description", product.get("description").asText());
        productMap.put("discountPercentage", product.get("discountPercentage").asText());
        productMap.put("rating", product.get("rating").asText());
        productMap.put("stock", product.get("stock").asText());
        productMap.put("brand", product.get("brand").asText());
        productMap.put("category", product.get("category").asText());
        return productMap;
    }

    public String getProductById(int id) {
        String getProductUrl = API_URL + "/" + id;
        return restTemplate.getForObject(getProductUrl, String.class);
    }

    public List<Map<String, Object>> getCustomizedProducts() {

        ResponseEntity<String> response = restTemplate.getForEntity("https://dummyjson.com/products", String.class);
        List<Map<String, Object>> customizedProducts = new ArrayList<>();

        try {
            JsonNode jsonNode = mapper.readTree(response.getBody());
            JsonNode productsNode = jsonNode.get("products");

            for (JsonNode productNode : productsNode) {
                if (productNode != null && productNode.get("id") != null) {
                    // Extract fields from the productNode and customize the response
                    Map<String, Object> customizedProduct = new LinkedHashMap<>();
                    customizedProduct.put("id", productNode.get("id").asInt());
                    customizedProduct.put("title", productNode.get("title").asText());
                    customizedProduct.put("description", productNode.get("description").asText());
                    customizedProduct.put("price", productNode.get("price").asText());
                    customizedProduct.put("discountPercentage", calculateDiscount(productNode.get("price").asDouble())); // Custom calculation for discount percentage
                    customizedProduct.put("rating", productNode.get("rating").asDouble());
                    customizedProduct.put("stock", productNode.get("stock").asText());
                    customizedProduct.put("brand", productNode.get("brand").asText());
                    customizedProduct.put("category", productNode.get("category").asText());
                    customizedProduct.put("thumbnail", productNode.get("thumbnail").asText());
                    customizedProduct.put("images", getProductImages(productNode)); // Extract images array
                    customizedProducts.add(customizedProduct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
        return customizedProducts;
    }

    // Custom method to calculate discount percentage
    private double calculateDiscount(double price) {
        // You can implement your own logic here to calculate discount percentage based on price
        // For simplicity, let's assume a fixed discount percentage of 10%
        return price * 0.1;
    }

    // Custom method to extract images from the product node
    private List<String> getProductImages(JsonNode productNode) {
        List<String> images = new ArrayList<>();
        for (JsonNode imageNode : productNode.get("images")) {
            images.add(imageNode.asText());
        }
        return images;
    }
}
