package mapper.example1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrderListMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orders = objectMapper.readValue(new File("src/main/resources/orderList.json"), new TypeReference<>() {
        });
        orders.forEach(System.out::println);
    }
}
