package mapper.example4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonArrayToObjectExample3 {
    public static void main(String[] args) throws JsonProcessingException {
        List<Person> people = Arrays.asList(
                new Person("Vijay", 42),
                new Person("Zaid", 20)
        );

        Map<String, List<Person>> wrapper = new HashMap<>();
        wrapper.put("Person", people);

        ObjectMapper mapper = new ObjectMapper();

        String jsonOutput = mapper.writeValueAsString(wrapper);

        System.out.println(jsonOutput);
    }
}
