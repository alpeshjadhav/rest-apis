package _tutorials;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestDemo1 {

	static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		String jsondata = getPost();
		System.out.println(jsondata);

	}

	public static String getPost() {
		//SET HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		//SET HttpEntity
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		//RETURN VALUE
		return restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, entity, String.class)
				.getBody();
	}

}
