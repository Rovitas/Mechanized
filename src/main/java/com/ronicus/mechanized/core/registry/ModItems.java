package com.ronicus.mechanized.core.registry;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.device.dome.DomeArmorItem;
import com.ronicus.mechanized.content.device.dome.LiteSpaceDomeItem;
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

    public static final DeferredItem<Item> LITE_SPACE_DOME = ITEMS.register("lite_space_dome",
            () -> new LiteSpaceDomeItem(new Item.Properties().stacksTo(1)));

    // Block Items
    public static final DeferredItem<BlockItem> ZOETIC_BLOOM = ITEMS.registerSimpleBlockItem("zoetic_bloom", ModBlocks.ZOETIC_BLOOM);
    public static final DeferredItem<BlockItem> DOME_TUNER = ITEMS.registerSimpleBlockItem("dome_tuner", ModBlocks.DOME_TUNER);
}
