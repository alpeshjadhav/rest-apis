package jsondemo;

import org.json.JSONObject;

public class JSONObjectDemo2 {
    public static void main(String[] args) {
        String jsonString = "{"
                + "\"data\":{"
                + "\"id\":\"123456789\","
                + "\"name\":\"Alpesh Jadhav\","
                + "\"firstName\":\"Alpesh\","
                + "\"lastName\":\"Jadhav\","
                + "\"email\":\"alpesh.jadhav@ies.in\","
                + "\"mobile\":\"9977884456\","
                + "\"department\":\"Information Technology\","
                + "\"designation\":\"Senior Software Engineer\","
                + "\"address\": {"
                + "    \"street\": \"123 Main St\","
                + "    \"city\": \"Anytown\","
                + "    \"postalCode\": \"12345\""
                + "}"
                + "}"
                + "}";

        JSONObject jsonObject = new JSONObject(jsonString);
        String id = jsonObject.getJSONObject("data").getString("id");
        System.out.println("ID: " + id);
    }
}
