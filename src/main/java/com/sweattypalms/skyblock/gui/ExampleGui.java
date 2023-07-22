package com.sweattypalms.skyblock.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ExampleGui extends GUI{
    public ExampleGui() {
        /*
         * | --------------------------------------------------------------- |
         * |  args[0] = for title                                            |
         * |  args[1] = for size                                             !
         * | ----------------------------------------------------------------|
         */
        super("Example", 54);

        // to fill inventory

        fill(BLACK_STAINED_GLASS_PANE);

        // to set item

        set(0 , new ItemStack(Material.NETHER_STAR));

        // to add pickable item

        set(1 , new ItemStack(Material.DIAMOND_SWORD) , true);


        // to add click event

        set(new GUIItem() {

            @Override
            public int getSlot() {
                return 2;
            }

            @Override
            public ItemStack getItem() {
                return new ItemStack(Material.OAK_BUTTON);
            }

            @Override
            public boolean canPickup() {
                return false;
            }

            @Override
            public void run(InventoryClickEvent e) {
                Player player = (Player) e.getWhoClicked();
                player.sendMessage("Clicked a Example gui");


            }
        });

    }
}
