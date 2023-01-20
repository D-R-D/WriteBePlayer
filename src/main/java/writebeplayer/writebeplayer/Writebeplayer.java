package writebeplayer.writebeplayer;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.geysermc.floodgate.api.FloodgateApi;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Plugin(
        id = "writebeplayer",
        name = "Writebeplayer",
        version = "1.0-SNAPSHOT"
)
public class Writebeplayer {
    private List<Map<Object,Object>> bePlayers;

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;
    private final FloodgateApi floodgateApi;

    @Inject
    public Writebeplayer(ProxyServer server, Logger logger,@DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        this.floodgateApi = FloodgateApi.getInstance();
        logger.info("WorkingDir : " + dataDirectory.toString());
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        try{
            InitConfig initConfig = new InitConfig(dataDirectory);
            initConfig.Init("BePlayerList.json",0);
        } catch (Exception ex){ logger.error("Failed InitConfig.Init ... ",ex); }

        try {
            String bePlayerString = ReadConfig.ReadAsString(dataDirectory, "BePlayerList.json");
            bePlayers = JsonSerializer.DeSerializeList(bePlayerString);
        }catch (Exception ex){ logger.error("Failed ReadConfig.ReadAsString ...",ex); }
    }

    @Subscribe
    public void onLoginEvent(LoginEvent login) {
        if(!floodgateApi.isFloodgatePlayer(login.getPlayer().getUniqueId())){
            return;
        }

        try {
            CheckPlayer checkPlayer = new CheckPlayer(login.getPlayer().getUsername(), String.valueOf(login.getPlayer().getUniqueId()), dataDirectory);
            if (!checkPlayer.Check()) {
                checkPlayer.WritePlayer();
                logger.info("Player[" + login.getPlayer().getUsername() + "] added in data.");
                return;
            }
        }catch (Exception ex){
            logger.error("In OnLoginEvent",ex);
        }
        logger.info("Player["+ login.getPlayer().getUsername() + "] found in data.");
    }
}
