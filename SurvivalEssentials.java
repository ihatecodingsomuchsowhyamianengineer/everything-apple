package com.novaventure.survivalessentials;

import com.novaventure.survivalessentials.registry.Blocks.Plants.Trees.AppleLeavesBlock;
import com.novaventure.survivalessentials.registry.Blocks.Plants.Trees.AppleLogBlock;
import com.novaventure.survivalessentials.registry.Blocks.Plants.Trees.Saplings.AppleSaplingBlock;
import com.novaventure.survivalessentials.registry.Features.BrownKelpFeature;
import com.novaventure.survivalessentials.registry.Features.Generators.AppleSaplingGenerator;
import com.novaventure.survivalessentials.registry.Features.OrangeKelpFeature;
import com.novaventure.survivalessentials.registry.Features.SEConfiguredFeatures;
import com.novaventure.survivalessentials.registry.Features.SeaweedFeature;
import com.novaventure.survivalessentials.registry.Blocks.Building_Materials.CharcoalBlock;
import com.novaventure.survivalessentials.registry.Blocks.Building_Materials.DriedBrownKelpBlock;
import com.novaventure.survivalessentials.registry.Blocks.Building_Materials.DriedOrangeKelpBlock;
import com.novaventure.survivalessentials.registry.Blocks.Building_Materials.DriedSeaweedBlock;
import com.novaventure.survivalessentials.registry.Blocks.Decorations.AnchorBlock;
import com.novaventure.survivalessentials.registry.Blocks.Minerals.SaltOreBlock;
import com.novaventure.survivalessentials.registry.Blocks.Plants.Underground.FaeryMushroomBase;
import com.novaventure.survivalessentials.registry.Blocks.Plants.Underground.GreenMushroomBase;
import com.novaventure.survivalessentials.registry.Blocks.Plants.Underwater.*;
import com.novaventure.survivalessentials.registry.Drinks.MilkBottleBase;
import com.novaventure.survivalessentials.registry.Flint.*;
import com.novaventure.survivalessentials.registry.Food.DriedBrownKelpBase;
import com.novaventure.survivalessentials.registry.Food.DriedOrangeKelpBase;
import com.novaventure.survivalessentials.registry.Food.DriedSeaweedBase;
import com.novaventure.survivalessentials.registry.Food.HoneyCombChunkBase;
import com.novaventure.survivalessentials.registry.FoodIngredients.SaltBase;
import com.novaventure.survivalessentials.registry.Jars.*;
import com.novaventure.survivalessentials.registry.Knives.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountNoiseBiasedDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

import static com.novaventure.survivalessentials.registry.Features.SaltOreFeature.ORE_SALT_OVERWORLD;
import static net.minecraft.world.biome.BiomeKeys.*;

public class SurvivalEssentials implements ModInitializer {

    public static final String MOD_ID = "novesues";

    //apple tree blocks
    public static final Block APPLE_LEAVES = new AppleLeavesBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1, 0.6f));

    public static final Block APPLE_SAPLING = new AppleSaplingBlock(new AppleSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT)
            .nonOpaque().noCollision().ticksRandomly().breakInstantly()
            .sounds(BlockSoundGroup.GRASS));

    public static final Block APPLE_LOG = new AppleLogBlock(FabricBlockSettings.of(Material.WOOD)
            .breakByHand(true)
            .sounds(BlockSoundGroup.WOOD)
            .strength(3, 3.0f));

    //mushrooms
    public static final Block FAERY_MUSHROOM = new FaeryMushroomBase(FabricBlockSettings.of(Material.PLANT)
            .breakByHand(true)
            .sounds(BlockSoundGroup.FUNGUS)
            .strength(1, 1.0f).luminance(10));

    public static final Block GREEN_MUSHROOM = new GreenMushroomBase(FabricBlockSettings.of(Material.PLANT)
            .breakByHand(true)
            .sounds(BlockSoundGroup.FUNGUS)
            .strength(1, 1.0f));

    //brown kelp
    public static final Block BROWN_KELP = new BrownKelpBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1, 0.1f));
    public static final Block BROWN_KELP_PLANT = new BrownKelpPlantBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1,0.1f));

    //seaweed
    public static final Block SEAWEED = new SeaweedBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1, 0.1f));
    public static final Block SEAWEED_PLANT = new SeaweedPlantBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1,0.1f));


    //orange kelp
    public static final Block ORANGE_KELP = new OrangeKelpBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1, 0.1f));
    public static final Block ORANGE_KELP_PLANT = new OrangeKelpPlantBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque()
            .breakByHand(true)
            .sounds(BlockSoundGroup.GRASS)
            .strength(1,0.1f));

    //features
    //faery mushroom gen
    //green mushroom gen
    //seaweed gen
    private static final Feature<DefaultFeatureConfig> OVERWORLD_SEAWEED_KELP_FEATURE = new SeaweedFeature(DefaultFeatureConfig.CODEC);

    public static final ConfiguredFeature<?, ?> OVERWORLD_SEAWEED_FEATURE_CONFIGURED = OVERWORLD_SEAWEED_KELP_FEATURE.configure(FeatureConfig.DEFAULT)
            .decorate(ConfiguredFeatures.Decorators.TOP_SOLID_HEIGHTMAP.spreadHorizontally())
            .decorate(Decorator.COUNT_NOISE_BIASED.configure(new CountNoiseBiasedDecoratorConfig(120, 80.0D, 0.0D)));

    //brown kelp gen
    private static final Feature<DefaultFeatureConfig> OVERWORLD_BROWN_KELP_FEATURE = new BrownKelpFeature(DefaultFeatureConfig.CODEC);

    public static final ConfiguredFeature<?, ?> OVERWORLD_BROWN_KELP_FEATURE_CONFIGURED = OVERWORLD_BROWN_KELP_FEATURE.configure(FeatureConfig.DEFAULT)
            .decorate(ConfiguredFeatures.Decorators.TOP_SOLID_HEIGHTMAP.spreadHorizontally())
            .decorate(Decorator.COUNT_NOISE_BIASED.configure(new CountNoiseBiasedDecoratorConfig(80, 80.0D, 0.0D)));

    //orange kelp gen
    private static final Feature<DefaultFeatureConfig> OVERWORLD_ORANGE_KELP_FEATURE = new OrangeKelpFeature(DefaultFeatureConfig.CODEC);

    public static final ConfiguredFeature<?, ?> OVERWORLD_ORANGE_KELP_FEATURE_CONFIGURED = OVERWORLD_ORANGE_KELP_FEATURE.configure(FeatureConfig.DEFAULT)
            .decorate(ConfiguredFeatures.Decorators.TOP_SOLID_HEIGHTMAP.spreadHorizontally())
            .decorate(Decorator.COUNT_NOISE_BIASED.configure(new CountNoiseBiasedDecoratorConfig(80, 80.0D, 0.0D)));

    public static final Block ANCHOR_BLOCK = new AnchorBlock();

    public static final Item SALT = new SaltBase(new Item.Settings().group(ItemGroup.MISC));
    public static final Block SALT_ORE = new SaltOreBlock();

    public static final Block CHARCOAL_BLOCK = new CharcoalBlock();

    public static final Item HONEYCOMB_CHUNK = new HoneyCombChunkBase(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,30), 1.0f).build()));
    public static final Item MILK_BOTTLE = new MilkBottleBase(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,30), 1.0f).build()));

    public static final Item JARPOTION_BASE = new JarPotionBase(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item GLISTERING_JAR = new GlisteringJamBase(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item BERRY_JAR = new SweetJamBase(new Item.Settings().group(ItemGroup.FOOD));

    public static final Item DRIED_SEAWEED = new DriedSeaweedBase(new Item.Settings().group(ItemGroup.FOOD));
    public static final Block DRIED_SEAWEED_BLOCK = new DriedSeaweedBlock();

    public static final Item DRIED_BROWN_KELP = new DriedBrownKelpBase(new Item.Settings().group(ItemGroup.FOOD));
    public static final Block DRIED_BROWN_KELP_BLOCK = new DriedBrownKelpBlock();

    public static final Item DRIED_ORANGE_KELP = new DriedOrangeKelpBase(new Item.Settings().group(ItemGroup.FOOD));
    public static final Block DRIED_ORANGE_KELP_BLOCK = new DriedOrangeKelpBlock();

    //building materials

    @Override
    public void onInitialize() {
        System.out.println("main mod initialization loaded fine™️");

        //trees
        SEConfiguredFeatures.registerConfiguredFeatures();
        //Apple Tree Blocks
        //Leaves
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "apple_leaves"), APPLE_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "apple_leaves"), new BlockItem(APPLE_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //Saplings
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "apple_sapling"), APPLE_SAPLING);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "apple_sapling"), new BlockItem(APPLE_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //Logs
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "apple_log"), APPLE_LOG);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "apple_log"), new BlockItem(APPLE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //mushrooms
        //Faery Mushroom
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "faery_mushroom"), FAERY_MUSHROOM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "faery_mushroom"), new BlockItem(FAERY_MUSHROOM, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //Green Mushroom
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "green_mushroom"), GREEN_MUSHROOM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_mushroom"), new BlockItem(GREEN_MUSHROOM, new Item.Settings().group(ItemGroup.DECORATIONS)));

        //Feature Generation
        //apple tree gen
        RegistryKey<ConfiguredFeature<?, ?>> scatteredtreeFeatureOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "scattered_apple_trees"));
        //noinspection deprecation
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FOREST, PLAINS), GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                scatteredtreeFeatureOverworld);
        //faery mushroom gen
        //green mushroom gen
        //new brown kelp gen
        RegistryKey<ConfiguredFeature<?, ?>> brownkelpFeatureOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "overworld_brown_kelp_feature"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, brownkelpFeatureOverworld.getValue(), OVERWORLD_BROWN_KELP_FEATURE_CONFIGURED);
        //noinspection deprecation
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(SWAMP, SWAMP_HILLS, JUNGLE, JUNGLE_HILLS, BAMBOO_JUNGLE_HILLS, BAMBOO_JUNGLE, MUSHROOM_FIELDS, MUSHROOM_FIELD_SHORE), GenerationStep.Feature.TOP_LAYER_MODIFICATION, brownkelpFeatureOverworld);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "overworld_brown_kelp_feature"), OVERWORLD_BROWN_KELP_FEATURE);
        //new seaweed gen
        RegistryKey<ConfiguredFeature<?, ?>> seaweedkelpFeatureOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "overworld_seaweed_kelp_feature"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, seaweedkelpFeatureOverworld.getValue(), OVERWORLD_SEAWEED_FEATURE_CONFIGURED);
        //noinspection deprecation
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(FROZEN_OCEAN, DEEP_FROZEN_OCEAN, COLD_OCEAN, DEEP_COLD_OCEAN, SNOWY_BEACH, STONE_SHORE, OCEAN, DEEP_OCEAN, BEACH), GenerationStep.Feature.VEGETAL_DECORATION, seaweedkelpFeatureOverworld);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "overworld_seaweed_kelp_feature"), OVERWORLD_SEAWEED_KELP_FEATURE);
        //new orange kelp gen
        RegistryKey<ConfiguredFeature<?, ?>> orangekelpFeatureOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "overworld_orange_kelp_feature"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, orangekelpFeatureOverworld.getValue(), OVERWORLD_ORANGE_KELP_FEATURE_CONFIGURED);
        //noinspection deprecation
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(DEEP_OCEAN, OCEAN, BEACH, STONE_SHORE), GenerationStep.Feature.TOP_LAYER_MODIFICATION, brownkelpFeatureOverworld);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "overworld_orange_kelp_feature"), OVERWORLD_ORANGE_KELP_FEATURE);
        //new salt ore gen
        RegistryKey<ConfiguredFeature<?, ?>> oreSaltOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_salt_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSaltOverworld.getValue(), ORE_SALT_OVERWORLD);
        //noinspection deprecation
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(DESERT, DESERT_LAKES, DESERT_HILLS, MODIFIED_BADLANDS_PLATEAU, MODIFIED_WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, BADLANDS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU, ERODED_BADLANDS, SAVANNA, SAVANNA_PLATEAU), GenerationStep.Feature.UNDERGROUND_ORES, oreSaltOverworld);

        //Bee/Honey Stuffs
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "honeycomb_chunk"), HONEYCOMB_CHUNK);

        //Drinks
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "milk_bottle"), MILK_BOTTLE);

        //Jarring
        //Empty Jar
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "jarpotion_base"), JARPOTION_BASE);
        //Jams
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "glistering_jam"), GLISTERING_JAR);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "berry_jam"), BERRY_JAR);
        //Spore Jars
        //Jar of Materials

        //Building Materials
        //Igusa
        //Tatami
        //Bamboo Blocks

        //Aquatic Stuff
        //Dried Seaweed/Block
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_seaweed"), DRIED_SEAWEED);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dried_seaweed_block"), DRIED_SEAWEED_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_seaweed_block"), new BlockItem(DRIED_SEAWEED_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Dried Brown Kelp/Block
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_brown_kelp"), DRIED_BROWN_KELP);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dried_brown_kelp_block"), DRIED_BROWN_KELP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_brown_kelp_block"), new BlockItem(DRIED_BROWN_KELP_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Dried Orange Kelp/Block
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_orange_kelp"), DRIED_ORANGE_KELP);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dried_orange_kelp_block"), DRIED_ORANGE_KELP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dried_orange_kelp_block"), new BlockItem(DRIED_ORANGE_KELP_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //brown kelp block & item
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "brown_kelp"), BROWN_KELP);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "brown_kelp_plant"), BROWN_KELP_PLANT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brown_kelp"), new BlockItem(BROWN_KELP, new Item.Settings().group(ItemGroup.FOOD)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brown_kelp_plant"), new BlockItem(BROWN_KELP_PLANT, new Item.Settings()));
        //seaweed block & item
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "seaweed"), SEAWEED);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "seaweed_plant"), SEAWEED_PLANT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "seaweed"), new BlockItem(SEAWEED, new Item.Settings().group(ItemGroup.FOOD)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "seaweed_plant"), new BlockItem(SEAWEED_PLANT, new Item.Settings()));
        //orange kelp block & item
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "orange_kelp"), ORANGE_KELP);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "orange_kelp_plant"), ORANGE_KELP_PLANT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orange_kelp"), new BlockItem(ORANGE_KELP, new Item.Settings().group(ItemGroup.FOOD)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orange_kelp_plant"), new BlockItem(ORANGE_KELP_PLANT, new Item.Settings()));




        //Minerals
        //Salt / Salt Ore
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "salt"), SALT);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "salt_ore"), SALT_ORE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "salt_ore"), new BlockItem(SALT_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Charcoal Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "charcoal_block"), new BlockItem(CHARCOAL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Aquatic Stuffs
        //Anchor
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "anchor"), ANCHOR_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "anchor"), new BlockItem(ANCHOR_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //Shipping Crates

        //Flint Tools
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_axe"), new FlintBaseAxe(new FlintToolMaterialAxe()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_pickaxe"), new FlintBasePickaxe(new FlintToolMaterialPickaxe()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_hoe"), new FlintBaseHoe(new FlintToolMaterialHoe()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_sword"), new FlintBaseSword(new FlintToolMaterialSword()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_shovel"), new FlintBaseShovel(new FlintToolMaterialShovel()));
        //Knives
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flint_knife"), new FlintKnifeBase(new FlintToolMaterialKnife()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_knife"), new StoneKnifeBase(new StoneToolMaterialKnife()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "golden_knife"), new GoldKnifeBase(new GoldenToolMaterialKnife()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_knife"), new IronKnifeBase(new IronToolMaterialKnife()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diamond_knife"), new DiamondKnifeBase(new DiamondToolMaterialKnife()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "netherite_knife"), new NetheriteKnifeBase(new NetheriteToolMaterialKnife()));
        //Sickles

    }
}
