package mapper.example4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonArrayToObjectExample4 {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonInput = "{\"Person\":[{\"name\":\"vijay\",\"age\":42},{\"name\":\"zaid\",\"age\":20}]}";

        ObjectMapper mapper = new ObjectMapper();

        Map<String, List<Person>> result = mapper.readValue(jsonInput, new TypeReference<>() {
        });

        List<Person> persons = result.get("Person");

        for (Person person : persons) {
            System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        }
    }
}
