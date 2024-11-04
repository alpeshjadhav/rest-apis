package _tutorials;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDemo2 {

	public static void main(String[] args) throws IOException {
	
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
				
		String BASE_URL = "https://dummyjson.com/products";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
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
		        for (JsonNode product : productsNode) {
		            System.out.println("Product ID: " + product.get("id").asText());
		            System.out.println("Product Name: " + product.get("title").asText());
		            System.out.println("Product Description: " + product.get("description").asText());
		            System.out.println("Product DiscountPercentage: " + product.get("discountPercentage").asText());
		            System.out.println("Product Rating: " + product.get("rating").asText());
		            System.out.println("Product Stock: " + product.get("stock").asText());
		            System.out.println("Product Brand: " + product.get("brand").asText());
		            System.out.println("Product Category: " + product.get("category").asText());
		            System.out.println("-------------------------");
		        }
		    } else {
		        throw new IOException("Unable to find 'products' array in the JSON response");
		    }
		} else {
		    throw new IOException("Failed to fetch data. Status code: " + responseEntity.getStatusCode());
		}
	}
}
