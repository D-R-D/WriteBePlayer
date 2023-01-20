package writebeplayer.writebeplayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WriteConfig {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static Path dataDirectory;
    private static String ConfigFile;

    public WriteConfig(Path dataDirectory, String ConfigFile){
        this.dataDirectory = dataDirectory;
        this.ConfigFile = ConfigFile;
    }

    public static void WriteString(String json) throws IOException {
        File file = new File(dataDirectory + "/" + ConfigFile);
        writeStringToFile(json,file);
    }

    public static void  WriteObject(Object object) throws IOException {
        File file = new File(dataDirectory + "/" + ConfigFile);
        writeStringToFile(mapper.writeValueAsString(object), file);
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}
