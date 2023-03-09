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

public class SetOther implements CommandExecutor {

    private final CustomPronounSet customPronounSet;

    public SetOther(CustomPronounSet pronounSet) {
        this.customPronounSet = pronounSet;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, @NotNull String[] args) {
        if (!cmd.getName().equalsIgnoreCase("axolotlpronouns") || !args[0].equalsIgnoreCase("setother")) {
            return false;
        }
        if (args.length != 3) {
            sender.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&cInvalid arguments."));
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);

        if (targetPlayer == null) {
            sender.sendMessage("Invalid Player!");
            return true;
        }

        UUID playerUUID = targetPlayer.getUniqueId();
        AxolotlPronouns.getInstance().getCustomPronounSet().setPronoun(playerUUID, args[2]);
        sender.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully set" + " " + "&e" + targetPlayer.getName() + " &apronouns to" + " " + "&e" + args[2]));
        return true;
    }
}
