package mapper.example4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class JsonArrayToObjectExample2 {
    public static void main(String[] args) throws JsonProcessingException {
        List<Person> list = Arrays.asList(
                new Person("Vijay", 42),
                new Person("Zaid", 20)
        );

        ObjectMapper mapper = new ObjectMapper();

        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

        System.out.println(result);
    }
}
