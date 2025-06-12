package com.ronicus.mechanized;

import com.ronicus.mechanized.device.DomeArmorItem;
import com.ronicus.mechanized.device.BasicDomeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mechanized.MODID);
    //Items
    public static final RegistryObject<Item> BOOSTER;
    public static final RegistryObject<Item> BASIC_DOME;
    //Blocks
    public static final RegistryObject<Item> ENERGETIC_FLOWER;
    public static final RegistryObject<Item> DOME_TUNER;

    //Items
    static {
        BOOSTER = ITEMS.register("booster", () -> new DomeArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));
        BASIC_DOME = ITEMS.register("basic_dome",() -> new BasicDomeItem(new Item.Properties().stacksTo(1)));
    }

    //Blocks
    static {
        ENERGETIC_FLOWER = ITEMS.register("energetic_flower", () -> new BlockItem(ModBlocks.ENERGETIC_FLOWER.get(), new Item.Properties()));
        DOME_TUNER = ITEMS.register("dome_tuner", () -> new BlockItem(ModBlocks.DOME_TUNER.get(), new Item.Properties()));
    }
}
