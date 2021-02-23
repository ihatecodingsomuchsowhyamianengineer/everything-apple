package com.novaventure.survivalessentials.registry.Features;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import static com.novaventure.survivalessentials.SurvivalEssentials.APPLE_LEAVES;
import static com.novaventure.survivalessentials.SurvivalEssentials.APPLE_LOG;
import static com.novaventure.survivalessentials.registry.Features.SEConfiguredFeatures.States.OAK_LOG;
import static net.minecraft.world.gen.feature.ConfiguredFeatures.*;

public class SEConfiguredFeatures {
    public static ConfiguredFeature<?, ?> APPLE_TREE_OVERWORLD;
    public static ConfiguredFeature<?, ?> SCATTERED_APPLE_TREES;

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<?, ?> configuredFeature) {
        return (ConfiguredFeature) Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    public static final class States {
        public static final BlockState APPLE_LOG;
        public static final BlockState APPLE_LEAVES;
        public static final BlockState OAK_LOG;

        static {
            APPLE_LOG = com.novaventure.survivalessentials.SurvivalEssentials.APPLE_LOG.getDefaultState();
            APPLE_LEAVES = com.novaventure.survivalessentials.SurvivalEssentials.APPLE_LEAVES.getDefaultState();
            OAK_LOG = Blocks.OAK_LOG.getDefaultState();
        }
    }

    public static void registerConfiguredFeatures() {

        //Apple Tree
        APPLE_TREE_OVERWORLD = register("novesues:apple_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(OAK_LOG),
                        new SimpleBlockStateProvider(SEConfiguredFeatures.States.APPLE_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(5, 2, 0),
                        new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
        //Apple Trees Gen
        SCATTERED_APPLE_TREES = register("novesues:scattered_apple_trees", Feature.RANDOM_SELECTOR.configure(
                new RandomFeatureConfig(ImmutableList.of(APPLE_TREE_OVERWORLD.withChance(0.33333334F)),
                        APPLE_TREE_OVERWORLD))
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                .decorate(Decorator.COUNT_EXTRA.configure(
                        new CountExtraDecoratorConfig(0, 0.05F, 1))));
    }
}
