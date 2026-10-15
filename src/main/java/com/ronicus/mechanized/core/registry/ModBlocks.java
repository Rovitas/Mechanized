package com.ronicus.mechanized.core.registry;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.machine.harmonizer.HarmonizerBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Mechanized.MODID);

    public static final DeferredBlock<Block> ZOETIC_BLOOM = BLOCKS.register("zoetic_bloom",
            () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).instabreak().sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> HARMONIZER = BLOCKS.register("harmonizer",
            () -> new HarmonizerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
}
