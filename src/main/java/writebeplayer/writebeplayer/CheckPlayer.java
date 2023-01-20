package writebeplayer.writebeplayer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckPlayer {
    private static Map<Object,Object> player;
    private static Path dataDirectory;

    public CheckPlayer(String name, String id, Path dataDirectory){
        player = new HashMap<>();
        player.put("name",name);
        player.put("id",id);

        this.dataDirectory = dataDirectory;
    }

    public static boolean Check() throws IOException {
        if(CheckFile(player,dataDirectory)){
            return true;
        }

        return false;
    }

    //プレイヤーが見つかったらtrue
    private static boolean CheckFile(Map<Object, Object> player, Path dataDirectory) throws IOException {
        String json = ReadConfig.ReadAsString(dataDirectory, "BePlayerList.json");
        ObjectMapper mapper = new ObjectMapper();

        List<Map<Object,Object>> mapList = mapper.readValue(json, new TypeReference<>() {});
        for (Map<Object,Object> map : mapList) {
            if(player.equals(map)){
                return true;
            }
        }

        return false;
    }

    public static void WritePlayer() throws IOException {
        String json = ReadConfig.ReadAsString(dataDirectory, "BePlayerList.json");
        ObjectMapper mapper = new ObjectMapper();

        List<Map<Object,Object>> mapList = mapper.readValue(json, new TypeReference<>() {});
        mapList.add(player);

        WriteConfig writeConfig = new WriteConfig(dataDirectory,"BePlayerList.json");
        writeConfig.WriteObject(mapList);
    }
}
