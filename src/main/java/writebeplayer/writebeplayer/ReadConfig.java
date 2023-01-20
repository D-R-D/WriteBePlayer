package writebeplayer.writebeplayer;

import java.io.*;
import java.nio.file.Path;

public class ReadConfig {
    public static String ReadAsString(Path dataDirectory, String configFile) throws IOException {
        Path jsonpath = Path.of(dataDirectory + "/" + configFile);
        File file = new File(jsonpath.toString());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = "";
        String result = "";
        while((str = br.readLine()) != null){
            result += str;
        }
        br.close();

        return result;
    }
}
