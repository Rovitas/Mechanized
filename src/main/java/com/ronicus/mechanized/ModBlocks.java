package com.ronicus.mechanized;

import com.ronicus.mechanized.machine.DomeTunerBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Mechanized.MODID);
    public static final RegistryObject<Block> ENERGETIC_FLOWER;
    public static final RegistryObject<Block> DOME_TUNER;

    static {
        ENERGETIC_FLOWER = BLOCKS.register("energetic_flower",() -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).noOcclusion()));

        DOME_TUNER = BLOCKS.register("dome_tuner", () -> new DomeTunerBlock(BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 7)
                .strength(0.5F)
                .noOcclusion()
        ));
    }
}
