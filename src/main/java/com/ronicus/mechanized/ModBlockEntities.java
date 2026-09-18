package com.ronicus.mechanized;

import com.ronicus.mechanized.machine.DomeTunerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Mechanized.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DomeTunerBlockEntity>> DOME_TUNER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dome_tuner",
                    () -> BlockEntityType.Builder.of(DomeTunerBlockEntity::new, ModBlocks.DOME_TUNER.get()).build(null));
}
