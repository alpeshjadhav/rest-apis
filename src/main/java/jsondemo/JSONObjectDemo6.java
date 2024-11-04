package jsondemo;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONObjectDemo6 {
    public static void main(String[] args) {
        String jsonString = "{"
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

        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        boolean isStudent = jsonObject.getBoolean("isStudent");
        JSONArray courses = jsonObject.getJSONArray("courses");
        JSONObject address = jsonObject.getJSONObject("address");

        String street = address.getString("street");
        String city = address.getString("city");
        String postalCode = address.getString("postalCode");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Is Student: " + isStudent);
        System.out.println("Courses: ");
        for (int i = 0; i < courses.length(); i++) {
            System.out.println("  - " + courses.getString(i));
        }
        System.out.println("Address: " + street + ", " + city + ", " + postalCode);

    }
}
