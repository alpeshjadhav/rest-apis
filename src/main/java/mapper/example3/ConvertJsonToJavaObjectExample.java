package mapper.example3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConvertJsonToJavaObjectExample {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Staff staff1 = MAPPER.readValue(new File("output.json"), Staff.class);
        String staff2PrettyPrint1 = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(staff1);
        System.out.println(staff2PrettyPrint1);

        String jsonInString = "{\"name\":\"zaid\",\"age\":37,\"skills\":[\"java\",\"python\"]}";
        Staff staff2 = MAPPER.readValue(jsonInString, Staff.class);
        String staff2PrettyPrint2 = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(staff2);
        System.out.println(staff2PrettyPrint2);
    }
}
