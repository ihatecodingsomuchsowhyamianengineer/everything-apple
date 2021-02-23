package com.novaventure.survivalessentials.registry.Blocks.Plants.Trees.Saplings;


import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class SESaplingBlock extends SaplingBlock {
    public SESaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    public SESaplingBlock(SaplingGenerator generator) {
        this(generator, Settings.copy(Blocks.OAK_SAPLING));
    }
}
