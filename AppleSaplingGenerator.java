package com.novaventure.survivalessentials.registry.Features.Generators;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static com.novaventure.survivalessentials.registry.Features.SEConfiguredFeatures.APPLE_TREE_OVERWORLD;

public class AppleSaplingGenerator extends SaplingGenerator {
    public AppleSaplingGenerator() {

    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        if (random.nextInt(10) == 0) {
            return bl ? ConfiguredFeatures.FANCY_OAK_BEES_005 : (ConfiguredFeature<TreeFeatureConfig, ?>) APPLE_TREE_OVERWORLD;
        } else {
            return bl ? ConfiguredFeatures.OAK_BEES_005 : (ConfiguredFeature<TreeFeatureConfig, ?>) APPLE_TREE_OVERWORLD;
        }
    }
}
