package json;

import Interfaces.IReadWriteFile;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadWriteJson implements IReadWriteFile {
    private JSONObject jsonObject;

    public JSONObject parseJSONFile(String filename) {
        String content = "N/A";

        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONObject(content);
    }

    public JSONObject parseJSONString(String data)
    {
        return new JSONObject(data);
    }


    @Override
    public void readFile(String path) {
        jsonObject = parseJSONFile(path);
    }

    @Override
    public void writeFile(List<List<String>>data, String path) {
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

    public HashMap<String, String> getFineCatalog(String path) {
        readFile(path);
        Iterator<String> keys = jsonObject.keys();
        HashMap<String, String> fineCatalog = new HashMap<>();
        while(keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                fineCatalog.put(jsonObject.getJSONObject(key).get("Geschwindigkeit").toString(), jsonObject.getJSONObject(key).get("Bussgeld").toString());
            }
        }
        return fineCatalog;
    }
}
