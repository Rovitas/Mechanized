package com.ronicus.mechanized;

import com.ronicus.mechanized.device.BasicDomeItem;
import com.ronicus.mechanized.device.DomeArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mechanized.MODID);

    // Items
    public static final DeferredItem<Item> BOOSTER = ITEMS.register("booster",
            () -> new DomeArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredItem<Item> BASIC_DOME = ITEMS.register("basic_dome",
            () -> new BasicDomeItem(new Item.Properties().stacksTo(1)));

    // Block Items
    public static final DeferredItem<BlockItem> ENERGETIC_FLOWER = ITEMS.registerSimpleBlockItem("energetic_flower", ModBlocks.ENERGETIC_FLOWER);
    public static final DeferredItem<BlockItem> DOME_TUNER = ITEMS.registerSimpleBlockItem("dome_tuner", ModBlocks.DOME_TUNER);
}
