package com.ronicus.mechanized;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Mechanized.MODID)
public class Mechanized {
    public static final String MODID = "mechanized";

    public Mechanized(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItemGroups.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
