package axolotlstudios.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexUtils {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String getHex(String msg) {
        Matcher matcher = pattern.matcher(msg);
        while (matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            matcher = pattern.matcher(msg);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String c(String s){
        return ChatColor.translateAlternateColorCodes('&', getHex(s));
    }
}
