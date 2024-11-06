package mapper.example4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonArrayToObjectExample {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = "[{\"name\":\"Vijay\", \"age\":42}, {\"name\":\"Zaid\", \"age\":20}]";

        System.out.println("Old Method: ");
        Person[] people = objectMapper.readValue(jsonArray, Person[].class);
        for (Person p : people){
            System.out.println(p);
        }

        System.out.println("\nNew Method: ");
        List<Person> list = objectMapper.readValue(jsonArray, new TypeReference<List<Person>>() {
        });
        list.forEach(System.out::println);
    }
}
