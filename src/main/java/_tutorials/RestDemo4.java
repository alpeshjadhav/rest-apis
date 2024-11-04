package _tutorials;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestDemo4 {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, String.class);
		
		System.out.println(responseEntity.getBody());

	}
}
