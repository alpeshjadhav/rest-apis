package mapper.example3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicJsonStringExample {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = "{\"name\": \"zaid\", \"age\": 20}";

            JsonNode node = objectMapper.readTree(json);
            System.out.println(node);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
