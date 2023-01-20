package writebeplayer.writebeplayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String SerializeMap(Map<Object,Object> objectMap) throws JsonProcessingException {
        return mapper.writeValueAsString(objectMap);
    }

    public static Map<Object,Object> DeSerializeMap(String json) throws JsonProcessingException {
        return mapper.readValue(json, new TypeReference<>() {});
    }

    public static String SerializeList(List<Map<Object,Object>> objectlist) throws JsonProcessingException {
        return  mapper.writeValueAsString(objectlist);
    }

    public static List<Map<Object,Object>> DeSerializeList(String json) throws JsonProcessingException {
        return mapper.readValue(json, new TypeReference<>() {});
    }
}
