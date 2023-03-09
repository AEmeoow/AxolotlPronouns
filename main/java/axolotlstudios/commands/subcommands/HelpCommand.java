package axolotlstudios.commands.subcommands;

import axolotlstudios.AxolotlPronouns;
import axolotlstudios.utils.HexUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("");
        sender.sendMessage(HexUtils.c("#FB5252" + AxolotlPronouns.getPrefix() + "&e&lSYSTEM COMMANDS:&r #FB5252      "));
        sender.sendMessage("");
        sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns help &7» &fGives a list of all accessible commands."));
        sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns reload &7» &fReload the configuration file."));
        sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns set &7» &fSet your pronouns."));
        sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns setunique &7» &fSet your pronouns to something unique."));
        sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns clear &7» &fClear your pronouns."));
        if (sender.hasPermission("axolotlstudios.admin")) {
            sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns setother &d<ign> &7» &fSet another players pronouns."));
            sender.sendMessage(HexUtils.c("     &8&l➥ #FB9DE9/axolotlpronouns clearother &d<ign> &7» &fClear another players pronouns."));
        }
        sender.sendMessage("");
        sender.sendMessage(HexUtils.c("&7➟ &fMade by &d&lAXOLOTL&f&lSTUDIOS"));
        sender.sendMessage("");
        return true;
    }
}
