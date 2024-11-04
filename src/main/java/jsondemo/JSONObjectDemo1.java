package jsondemo;

import org.json.JSONObject;

public class JSONObjectDemo1 {
    public static void main(String[] args) {
        String jsonString = "{"
                + "\"id\":\"123456789\","
                + "\"name\": \"John Doe\","
                + "\"age\": 30,"
                + "\"isStudent\": false,"
                + "\"courses\": [\"Math\", \"Science\", \"History\"],"
                + "\"address\": {"
                + "    \"street\": \"123 Main St\","
                + "    \"city\": \"Anytown\","
                + "    \"postalCode\": \"12345\""
                + "}"
                + "}";

        JSONObject jsonObject = new JSONObject(jsonString);
        String id = jsonObject.getString("id");
        System.out.println("ID: " + id);
    }
}
