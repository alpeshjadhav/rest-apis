package fakestoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FakeStoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FakeStoreApiApplication.class);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
