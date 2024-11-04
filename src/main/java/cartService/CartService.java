package cartService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CartService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private static final String API_URL = "https://dummyjson.com/carts";

    public String getCarts() throws IOException {
        //TODO: get cart list with all field
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
            JsonNode cartsNode = jsonNode.get("carts");

            if (cartsNode != null && cartsNode.isArray()) {
                List<Map<String, Object>> cartList =
                        mapper.readValue(cartsNode.toPrettyString(),
                                mapper.getTypeFactory()
                                        .constructCollectionType(List.class, LinkedHashMap.class));

                return mapper.writeValueAsString(cartList);
            } else {
                throw new IOException("Unable to find 'carts' array in the JSON response");
            }
        } else {
            throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
        }
    }
}
