package mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadValueJavaObjectFromFile {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(new File("src/main/resources/car.json"), Car.class);
        System.out.println(car);
    }
}
