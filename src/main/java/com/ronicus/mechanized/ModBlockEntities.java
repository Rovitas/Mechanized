package com.ronicus.mechanized;

import com.ronicus.mechanized.machine.DomeTunerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Mechanized.MODID);
    public static final RegistryObject<BlockEntityType<?>> DOME_TUNER_BLOCK_ENTITY;

    static {
        DOME_TUNER_BLOCK_ENTITY = BLOCK_ENTITIES.register("dome_tuner_block_entity",
                () -> BlockEntityType.Builder.of(DomeTunerBlockEntity::new,
                        ModBlocks.DOME_TUNER.get()).build(null));
    }
}
