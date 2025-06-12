package com.ronicus.mechanized;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItemGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mechanized.MODID);
    public static final RegistryObject<CreativeModeTab> MAIN;

    static {
        MAIN = CREATIVE_MODE_TABS.register(
                "mechanized", () -> CreativeModeTab.builder()
                        .title(Component.translatable("item_group.mechanized.main"))
                        .icon(() -> ModItems.BASIC_DOME.get().getDefaultInstance())
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.ENERGETIC_FLOWER.get());
                            output.accept(ModItems.DOME_TUNER.get());
                            output.accept(ModItems.BASIC_DOME.get());
                        })
                        .build()
        );
    }
}
