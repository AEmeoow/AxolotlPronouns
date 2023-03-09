package axolotlstudios.commands.subcommands;

import axolotlstudios.AxolotlPronouns;
import axolotlstudios.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClearOther implements CommandExecutor {

    private final CustomPronounSet customPronounSet;

    public ClearOther(CustomPronounSet pronounSet) {
        this.customPronounSet = pronounSet;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, @NotNull String[] args) {
        if (!cmd.getName().equalsIgnoreCase("axolotlpronouns") || !args[0].equalsIgnoreCase("clearother")) {
            return false;
        }
        if (args.length != 2) {
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);

        if (targetPlayer == null) {
            sender.sendMessage("Invalid Player!");
            return true;
        }

        UUID playerUUID = targetPlayer.getUniqueId();
        AxolotlPronouns.getInstance().getCustomPronounSet().removePronoun(playerUUID);
        sender.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully cleared" +  " " + "&e" + targetPlayer.getName() + " " + "&apronouns."));
        return true;
    }
}