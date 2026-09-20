package com.ronicus.mechanized;

import com.ronicus.mechanized.device.BasicDomeItem;
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
                    .icon(() -> BasicDomeItem.createStack(BasicDomeItem.STATE_IDLE))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.BOOSTER.get());
                        output.accept(ModItems.BASIC_DOME.get());
                        output.accept(ModItems.ENERGETIC_FLOWER.get());
                        output.accept(ModItems.DOME_TUNER.get());
                    })
                    .build());
}
