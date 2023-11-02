package json;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {
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
        build();
        JSONObject jsonObject = parseJSONFile("configuration.json");
        System.out.println(jsonObject);
    }

    public static void build() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("configuration.json"));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("setting 01", "abc");
            jsonObject.put("setting 02", "xyz");

            writer.write(jsonObject.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}