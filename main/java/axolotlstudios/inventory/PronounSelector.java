package axolotlstudios.inventory;

import axolotlstudios.AxolotlPronouns;
import axolotlstudios.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PronounSelector implements Listener {

    public static void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 27, "⋙ PRONOUN SELECTOR ⋘");

        ItemStack setMale = new ItemStack(Material.PAPER);
        ItemMeta setMaleItemMeta = setMale.getItemMeta();
        setMaleItemMeta.setDisplayName(HexUtils.c("&f⋙ &b&lhe/him &f⋘"));
        setMaleItemMeta.setLore(Arrays.asList(
                "",
                HexUtils.c("&7- &fSelect to set your pronouns as he/him!"),
                HexUtils.c("")
        ));
        setMale.setItemMeta(setMaleItemMeta);
        gui.setItem(11, setMale);

        ItemStack setFemale = new ItemStack(Material.PAPER);
        ItemMeta setFemaleMeta = setFemale.getItemMeta();
        setFemaleMeta.setDisplayName(HexUtils.c("&f⋙ &d&lshe/her &f⋘"));
        setFemaleMeta.setLore(Arrays.asList(
                "",
                HexUtils.c("&7- &fSelect to set your pronouns as she/her!"),
                HexUtils.c("")
        ));

        setFemale.setItemMeta(setFemaleMeta);
        gui.setItem(13, setFemale);

        ItemStack setNonny = new ItemStack(Material.PAPER);
        ItemMeta setNonnyItemMeta = setNonny.getItemMeta();
        setNonnyItemMeta.setDisplayName(HexUtils.c("&f⋙ &7&lthey/them &f⋘"));
        setNonnyItemMeta.setLore(Arrays.asList(
                "",
                HexUtils.c("&7- &fSelect to set your pronouns as they/them!"),
                HexUtils.c("")
        ));
        setNonny.setItemMeta(setNonnyItemMeta);
        gui.setItem(15, setNonny);

        ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta grayGlassMeta = grayGlass.getItemMeta();
        grayGlassMeta.setDisplayName("");
        grayGlassMeta.setLocalizedName(" ");
        grayGlass.setItemMeta(grayGlassMeta);

        for (int i = 0; i < 27; i++) {
            if (gui.getItem(i) == null) {
                gui.setItem(i, grayGlass);
            }
        }

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!event.getView().getTitle().equals("⋙ PRONOUN SELECTOR ⋘")) {
            return;
        }

        event.setCancelled(true);
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && clickedItem.hasItemMeta()) {
            if (clickedItem.hasItemMeta()) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (event.getSlot() == 11) {
                    player.closeInventory();
                    player.sendMessage("");
                    AxolotlPronouns.getInstance().getCustomPronounSet().setPronoun(player.getUniqueId(), "he/him");
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully set your pronouns to he/him."));
                }

                else if (event.getSlot() == 13) {
                    player.closeInventory();
                    player.sendMessage(HexUtils.c(""));
                    AxolotlPronouns.getInstance().getCustomPronounSet().setPronoun(player.getUniqueId(), "she/her");
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully set your pronouns to she/her."));
                }

                else if (event.getSlot() == 15) {
                    player.closeInventory();
                    player.sendMessage(HexUtils.c(""));
                    AxolotlPronouns.getInstance().getCustomPronounSet().setPronoun(player.getUniqueId(), "they/them");
                    player.sendMessage(HexUtils.c(AxolotlPronouns.getPrefix() + "&aSuccessfully set your pronouns to theu/them."));
                }
            }
        }
    }
}
