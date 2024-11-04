package jsondemo;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONObjectDemo4 {
    public static void main(String[] args) {
        String jsonString = "{"
                + "\"data\": [{"
                + "    \"type\": \"articles\","
                + "    \"id\": \"1\","
                + "    \"attributes\": {"
                + "      \"title\": \"JSON API paints my bikeshed!\","
                + "      \"body\": \"The shortest article. Ever.\","
                + "      \"created\": \"2015-05-22T14:56:29.000Z\","
                + "      \"updated\": \"2015-05-22T14:56:28.000Z\""
                + "    },"
                + "    \"relationships\": {"
                + "      \"author\": {"
                + "        \"data\": {\"id\": \"42\", \"type\": \"people\"}"
                + "      }"
                + "    }"
                + "  }],"
                + "\"included\": ["
                + "  {"
                + "    \"type\": \"people\","
                + "    \"id\": \"42\","
                + "    \"attributes\": {"
                + "      \"name\": \"John\","
                + "      \"age\": 80,"
                + "      \"gender\": \"male\""
                + "    }"
                + "  }"
                + "]"
                + "}";

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray dataArray = jsonObject.getJSONArray("data");

        if (dataArray != null && !dataArray.isEmpty()) {
            JSONObject firstDataObject = dataArray.getJSONObject(0);
            JSONObject attributes = firstDataObject.getJSONObject("attributes");
            String title = attributes.getString("title");

            System.out.println("Title: " + title);
        }
    }
}
