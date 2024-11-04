package _tutorials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDemo3 {

    public static void main(String[] args) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        String BASE_URL = "https://dummyjson.com/products";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                BASE_URL,
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
                    Map<String, Object> productMap = new HashMap<>();
                    productMap.put("id", product.get("id").asText());
                    productMap.put("title", product.get("title").asText());
                    productMap.put("description", product.get("description").asText());
                    productMap.put("discountPercentage", product.get("discountPercentage").asText());
                    productMap.put("rating", product.get("rating").asText());
                    productMap.put("stock", product.get("stock").asText());
                    productMap.put("brand", product.get("brand").asText());
                    productMap.put("category", product.get("category").asText());

                    productList.add(productMap);
                }

                // Now productList contains a list of Map<String, Object> representing each product
                for (Map<String, Object> productMap : productList) {
                    System.out.println(productMap);
                }
            } else {
                throw new IOException("Unable to find 'products' array in the JSON response");
            }
        } else {
            throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
        }
    }
}
