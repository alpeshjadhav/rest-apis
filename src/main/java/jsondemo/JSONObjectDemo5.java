package jsondemo;

import org.json.JSONObject;

public class JSONObjectDemo5 {
    public static void main(String[] args) {
        String jsonString = "{"
                + "\"data\": {"
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
                + "  }"
                + "}";

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject dataObject = jsonObject.getJSONObject("data");
        JSONObject attributes = dataObject.getJSONObject("attributes");
        String title = attributes.getString("title");

        String id  = jsonObject.getJSONObject("data").getJSONObject("relationships").getJSONObject("author").getJSONObject("data").getString("id");

        System.out.println("Title: " + title);
        System.out.println("Author ID: "+id);
    }
}
