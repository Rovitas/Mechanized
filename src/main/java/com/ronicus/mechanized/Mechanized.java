package com.ronicus.mechanized;

import com.ronicus.mechanized.client.BasicDomeCurioRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(Mechanized.MODID)
public class Mechanized {
    public static final String MODID = "mechanized";

    public Mechanized(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModItemGroups.CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(ModItems.BASIC_DOME.get(), BasicDomeCurioRenderer::new);
    }
}
