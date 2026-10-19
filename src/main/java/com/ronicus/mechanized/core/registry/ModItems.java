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

    // Phase 1 Materials - Steel Chain
    public static final DeferredItem<Item> RAW_STEEL_BLEND = ITEMS.registerSimpleItem("raw_steel_blend", new Item.Properties());
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerSimpleItem("steel_ingot", new Item.Properties());
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.registerSimpleItem("steel_plate", new Item.Properties());
    public static final DeferredItem<Item> STEEL_ROD = ITEMS.registerSimpleItem("steel_rod", new Item.Properties());
    public static final DeferredItem<Item> METAL_SCRAP = ITEMS.registerSimpleItem("metal_scrap", new Item.Properties());

    // Phase 1 Materials - Silicon & Electronics Chain
    public static final DeferredItem<Item> SILICON_WAFER = ITEMS.registerSimpleItem("silicon_wafer", new Item.Properties());
    public static final DeferredItem<Item> COPPER_WIRE = ITEMS.registerSimpleItem("copper_wire", new Item.Properties());
    public static final DeferredItem<Item> CIRCUIT_SCRAP = ITEMS.registerSimpleItem("circuit_scrap", new Item.Properties());

    // Block Items
    public static final DeferredItem<BlockItem> ZOETIC_BLOOM = ITEMS.registerSimpleBlockItem("zoetic_bloom", ModBlocks.ZOETIC_BLOOM);
    public static final DeferredItem<BlockItem> HARMONIZER = ITEMS.registerSimpleBlockItem("harmonizer", ModBlocks.HARMONIZER);
}
