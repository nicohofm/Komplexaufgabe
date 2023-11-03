package json;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Application {

    private static Map<String, String> fineCatalog = new HashMap<>();

    public static JSONObject parseJSONFile(String filename) {
        String content = "N/A";

        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONObject(content);
    }

    public static void main(String... args) {
        JSONObject jsonObject = parseJSONFile("./data/bussgeld.json");
        Iterator<String> keys = jsonObject.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                fineCatalog.put(jsonObject.getJSONObject(key).get("Geschwindigkeit").toString(), jsonObject.getJSONObject(key).get("Bussgeld").toString());
            }
        }
    }

    public static void build() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("bussgeld.json"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("setting 01", "abc");
            jsonObject.put("setting 02", "xyz");

            writer.write(jsonObject.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String, String> getFineCatalog() {
        return fineCatalog;
    }
}