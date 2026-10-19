package com.ronicus.mechanized.core.registry;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.device.dome.LiteSpaceDomeItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItemGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, Mechanized.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            CREATIVE_MODE_TABS.register("mechanized_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.mechanized.main"))
                    .icon(() -> LiteSpaceDomeItem.createStack(LiteSpaceDomeItem.STATE_IDLE))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.LITE_SPACE_DOME.get());
                        output.accept(ModItems.ZOETIC_BLOOM.get());
                        output.accept(ModItems.HARMONIZER.get());

                        // Phase 1 Materials - Steel Chain
                        output.accept(ModItems.RAW_STEEL_BLEND.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.STEEL_PLATE.get());
                        output.accept(ModItems.STEEL_ROD.get());
                        output.accept(ModItems.METAL_SCRAP.get());

                        // Phase 1 Materials - Silicon & Electronics Chain
                        output.accept(ModItems.SILICON_WAFER.get());
                        output.accept(ModItems.COPPER_WIRE.get());
                        output.accept(ModItems.CIRCUIT_SCRAP.get());
                    })
                    .build());
}
