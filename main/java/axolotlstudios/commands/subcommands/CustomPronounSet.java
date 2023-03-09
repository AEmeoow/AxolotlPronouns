package axolotlstudios.commands.subcommands;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class CustomPronounSet {
    private HashMap<UUID, String> pronounMap = new HashMap<>();

    private File dataFile;
    private YamlConfiguration dataConfig;

    public void setPronoun(UUID playerUUID, String pronoun) {
        this.pronounMap.put(playerUUID, pronoun);
        this.getDataConfig().set(playerUUID.toString(), pronoun);
        this.saveDataConfig();
    }

    public void removePronoun(UUID playerUUID) {
        this.pronounMap.remove(playerUUID);
        this.getDataConfig().set(playerUUID.toString(), null);
        this.saveDataConfig();
    }

    public String getPronoun(UUID playerUUID) {
        if (this.pronounMap.containsKey(playerUUID)) return this.pronounMap.get(playerUUID);

        String pronoun = this.getDataConfig().getString(playerUUID.toString(),null);
        if (pronoun != null) {
            this.pronounMap.put(playerUUID, pronoun);
            return pronoun;
        } else {
            return null;
        }
    }

    private File getDataFolder() {
        return new File("plugins/AxolotlPronouns");
    }

    private YamlConfiguration getDataConfig() {
        if (this.dataConfig != null) return this.dataConfig;

        try {
            this.dataFile = new File(this.getDataFolder(), "data.yml");
            if (!dataFile.exists()) dataFile.createNewFile();

            this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);
            return this.dataConfig;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveDataConfig() {
        try {
            if (this.dataConfig == null) this.getDataConfig();
            this.dataConfig.save(this.dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

