package receiver_pack;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 09.05.2017.
 */
public class SimpleParser {
    public Map<String,String> getData(String json){
        StringReader r = new StringReader(json);
        JsonParser parser = Json.createParser(r);
        boolean tmp = true;
        Map<String,String> m = new HashMap<>();
        String key = "";
        String value = "";
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            if (event.equals(JsonParser.Event.KEY_NAME)) {
                key = parser.getString();
            }
            if (event.equals(JsonParser.Event.VALUE_STRING)) {
                value = parser.getString();
                m.put(key, value);
            }
        }
        return m;
    }

    public Map<String, String> getWeather(String json){
        Map<String,String> map;
        map = getData(json);
        return detrancate(map);
    }

    public Map<String, String> getWeather(Map<String,String> map){
        return detrancate(map);
    }

    public int getId(Map<String,String> map){
        return Integer.parseInt(map.get("id"));
    }

    public long getTimestamp(Map<String,String> map){
        return Long.parseLong(map.get("timestamp"));
    }

    private Map<String,String> detrancate(Map<String,String> map){
        map.remove("name");
        map.remove("timestamp");
        return map;
    }

    public static void main(String[] args) {
        SimpleParser par = new SimpleParser();
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"key1\"");
        sb.append(":");
        sb.append("\"value1\"");
        sb.append(",");

        sb.append("\"key2\"");
        sb.append(":");
        sb.append("\"value2\"");
        sb.append(",");

        sb.append("\"key3\"");
        sb.append(":");
        sb.append("\"value3\"");
//        sb.append("\",\"");
        sb.append("}");
        System.out.println(sb);
        String json = new String(sb);
        Map<String, String> map;
        map = par.getData(json);
        System.out.println(map);
    }
}
