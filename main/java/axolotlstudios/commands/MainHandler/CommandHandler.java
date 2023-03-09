package axolotlstudios.commands.MainHandler;

import axolotlstudios.AxolotlPronouns;
import axolotlstudios.commands.subcommands.ClearOther;
import axolotlstudios.commands.subcommands.CustomPronounSet;
import axolotlstudios.commands.subcommands.HelpCommand;
import axolotlstudios.commands.subcommands.SetOther;
import axolotlstudios.inventory.PronounSelector;
import axolotlstudios.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandHandler implements CommandExecutor, TabCompleter {

    HelpCommand helpCommand = new HelpCommand();
    ClearOther clearOther;

    SetOther setOther;

    CustomPronounSet customPronounSet = new CustomPronounSet();

    PronounSelector pronounSelector = new PronounSelector();

    public CommandHandler(CustomPronounSet pronounSet) {
        this.clearOther = new ClearOther(pronounSet);
        this.setOther = new SetOther(pronounSet);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        if (args.length == 0) {
            helpCommand.onCommand(sender, command, label, args);
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (player.hasPermission("axolotlstudios.use")) {
            if (args[0].equalsIgnoreCase("help")) {
                helpCommand.onCommand(sender, command, label, args);
                return true;
            } else if (args[0].equalsIgnoreCase("set")) {
                pronounSelector.openGUI(player);
                return true;
            } else if (args[0].equalsIgnoreCase("clear")) {
                AxolotlPronouns.getInstance().getCustomPronounSet().removePronoun(playerUUID);
                player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully cleared your pronouns."));
                return true;
            } else if (args[0].equalsIgnoreCase("setunique")) {
                if (AxolotlPronouns.getInstance().getConfig().getBoolean("custom-pronouns") == true) {
                    AxolotlPronouns.getInstance().getCustomPronounSet().setPronoun(player.getUniqueId(), args[1]);
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully set pronouns to" + " " + "&e" + args[1]));
                    return true;
                }
                else {
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&cThis feature is disabled."));
                }
            }

            if (player.hasPermission("axolotlstudios.admin")) {
                if (args[0].equalsIgnoreCase("setother")) {
                    setOther.onCommand(sender, command, label, args);
                } else if (args[0].equalsIgnoreCase("clearother")) {
                    clearOther.onCommand(sender, command, label, args);
                } else if (args[0].equalsIgnoreCase("reload")) {
                    AxolotlPronouns.getInstance().reloadConfig();
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully reloaded the configuration file."));
                }
            }
        } else {
            player.sendMessage(HexUtils.c("&cYou do not have permission."));
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("help");
            completions.add("set");
            completions.add("clear");
            completions.add("setunique");

            if (sender.hasPermission("axolotlstudios.admin")) {
                completions.add("setother");
                completions.add("clearother");
                completions.add("reload");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("setother") || (args[0].equalsIgnoreCase("clearother"))) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }

            return completions;

        } else if (args.length == 3 && args[0].equalsIgnoreCase("setother")) {
            completions.add("he/him");
            completions.add("she/her");
            completions.add("they/them");

        }
        return completions;
    }
}