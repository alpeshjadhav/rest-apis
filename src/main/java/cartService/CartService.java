package cartService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class CartService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private static final String API_URL = "https://dummyjson.com/carts";

    public String getCarts() throws IOException {
        // Set headers to request JSON data
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make GET request to the API
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                String.class
        );

        // Process the response if it's successful
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JsonNode jsonNode = mapper.readTree(responseEntity.getBody());
            JsonNode cartsNode = jsonNode.get("carts");

            // Check for the "carts" array in the JSON response and return it as a JSON string
            if (cartsNode != null && cartsNode.isArray()) {
                return mapper.writeValueAsString(cartsNode);
            } else {
                throw new IOException("Unable to find 'carts' array in the JSON response");
            }
        } else {
            throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
        }
    }
}
