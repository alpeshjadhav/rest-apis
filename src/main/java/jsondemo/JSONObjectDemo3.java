package jsondemo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONObjectDemo3 {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

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

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray productsArray = jsonObject.getJSONArray("products");

            if (productsArray != null) {
                List<Map<String, Object>> productList = new ArrayList<>();
                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject product = productsArray.getJSONObject(i);
                    Map<String, Object> productMap = new HashMap<>();
                    productMap.put("id", product.get("id"));
                    productList.add(productMap);
                }
                // Now productList contains a list of Map<String, Object> representing each product
                for (Map<String, Object> productMap : productList) {
                    System.out.println(productMap);
                }
            }
        } else {
            System.out.println("Failed to fetch data: " + responseEntity.getStatusCode());
        }
    }
}
