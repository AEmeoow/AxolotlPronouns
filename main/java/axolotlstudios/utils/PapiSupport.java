package axolotlstudios.utils;

import axolotlstudios.AxolotlPronouns;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PapiSupport extends PlaceholderExpansion{

    private final AxolotlPronouns plugin;

    public PapiSupport(AxolotlPronouns plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "axolotlpronouns";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Chillz";
    }

    @Override
    public @NotNull String getVersion() {
        return "1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("mypronoun")) { // %axolotlpronouns_mypronoun%
            String pronoun = AxolotlPronouns.getInstance().getCustomPronounSet().getPronoun(player.getUniqueId());

            if (pronoun == null) return "None";

            if (pronoun.equals("he/him")) return "Male";

            if (pronoun.equals("she/her")) return "Female";

            if (pronoun.equals("they/them")) return "Nonbinary";

            return "Other";

        }

        return null;
    }
}
