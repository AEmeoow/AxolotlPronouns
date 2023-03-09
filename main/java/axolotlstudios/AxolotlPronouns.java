package axolotlstudios;

import axolotlstudios.commands.MainHandler.CommandHandler;
import axolotlstudios.commands.subcommands.CustomPronounSet;
import axolotlstudios.inventory.PronounSelector;
import axolotlstudios.utils.PapiSupport;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxolotlPronouns extends JavaPlugin {

    private static AxolotlPronouns instance;

    private CustomPronounSet customPronounSet;

    private static String prefix;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveDefaultConfig();
        reloadConfig();
        prefix = getConfig().getString("prefix");

        customPronounSet = new CustomPronounSet();

        getServer().getPluginManager().registerEvents(new PronounSelector(), this);
        getCommand("axolotlpronouns").setExecutor(new CommandHandler(customPronounSet));

        // Register the placeholder api hook
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PapiSupport(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static AxolotlPronouns getInstance(){
        return instance;
    }

    public CustomPronounSet getCustomPronounSet(){
        return customPronounSet;
    }

    public static String getPrefix() {
        return prefix;
    }


}
