package mapper.example3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicJsonArrayExample {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = "[" +
                    "{\"name\": \"Raj\", \"age\": 20}," +
                    "{\"name\": \"Mahesh\", \"age\": 40}," +
                    "{\"name\": \"Mayur\", \"age\": 30}" +
                    "]";

            JsonNode arrayNode = mapper.readTree(json);
            if (arrayNode.isArray()) {
                for (JsonNode node : arrayNode) {
                    System.out.println("Name: " + node.get("name").asText());
                    System.out.println("Age: " + node.get("age").asInt());
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
