package mapper.example1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class OrderMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(new File("src/main/resources/order.json"), Order.class);
        System.out.println(order);
    }
}
