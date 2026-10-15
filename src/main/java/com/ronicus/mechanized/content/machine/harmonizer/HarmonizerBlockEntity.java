package com.ronicus.mechanized.content.machine.harmonizer;

import com.ronicus.mechanized.core.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HarmonizerBlockEntity extends BlockEntity {
    public HarmonizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HARMONIZER_BLOCK_ENTITY.get(), pos, state);
    }
}
