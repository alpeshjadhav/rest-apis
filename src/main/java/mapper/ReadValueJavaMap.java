package mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ReadValueJavaMap {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {
        });

        map.forEach((s, o) -> System.out.println(s+":"+o));
    }
}
