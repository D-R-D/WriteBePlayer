package writebeplayer.writebeplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class InitConfig {
    private static Path dataDirectory;

    public InitConfig(Path dataDirectory){
        this.dataDirectory = dataDirectory;
    }

     public static void Init(String ConfigFile, int InitType) throws IOException {
         if (!Files.exists(dataDirectory)) {
             Files.createDirectories(dataDirectory);
         }

         if (!Files.exists(Path.of(dataDirectory + "/" + ConfigFile))) {
             Files.createFile(Path.of(dataDirectory + "/" + ConfigFile));
         }

         String content = ReadConfig.ReadAsString(dataDirectory, ConfigFile);
         WriteConfig writeConfig = new WriteConfig(dataDirectory, ConfigFile);

         if (!content.equals(null) && !content.equals("")) {
             return;
         }

         //InitType == 0 ... Blank List
         if (InitType == 0) {
             writeConfig.WriteObject(new ArrayList<>());
         }
     }
}
