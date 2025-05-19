package com.ronicus.mechanized.machine;

import com.ronicus.mechanized.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DomeTunerBlockEntity extends BlockEntity {
    public DomeTunerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DOME_TUNER_BLOCK_ENTITY.get(), pos, state);
    }
}
