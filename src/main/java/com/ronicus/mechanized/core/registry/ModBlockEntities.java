package com.ronicus.mechanized.core.registry;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.machine.harmonizer.HarmonizerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Mechanized.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HarmonizerBlockEntity>> HARMONIZER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("harmonizer",
                    () -> BlockEntityType.Builder.of(HarmonizerBlockEntity::new, ModBlocks.HARMONIZER.get()).build(null));
}
