package geoves.mineralogy.world;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_SLAG = registryKey("copper_slag_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRON_SLAG = registryKey("iron_slag_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLD_SLAG = registryKey("gold_slag_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COOLED_GOLD_SLAG = registryKey("gold_slag_cooled_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COOLED_IRON_SLAG = registryKey("iron_slag_cooled_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COOLED_COPPER_SLAG = registryKey("copper_slag_cooled_block");

    public static final TagKey<Block> COPPER_SLAG_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("mineralogy", "copper_slag_replaceable"));
    public static final TagKey<Block> COOLED_SLAG_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("mineralogy", "cooled_slag_replaceable"));
    public static final TagKey<Block> IRON_SLAG_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("mineralogy", "iron_slag_replaceable"));
    public static final TagKey<Block> GOLD_SLAG_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("mineralogy", "gold_slag_replaceable"));
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest cooledReplaceables = new TagMatchRuleTest(COOLED_SLAG_REPLACEABLE);
        RuleTest ironSlagReplaceables = new TagMatchRuleTest(IRON_SLAG_REPLACEABLE);
        RuleTest copperSlagReplaceables = new TagMatchRuleTest(COPPER_SLAG_REPLACEABLE);
        RuleTest goldSlagReplaceables = new TagMatchRuleTest(GOLD_SLAG_REPLACEABLE);


        List<OreFeatureConfig.Target> worldCopperSlags =
                List.of(OreFeatureConfig.createTarget(copperSlagReplaceables, ModBlocks.COPPER_SLAG_BLOCK.getDefaultState()),
                        OreFeatureConfig.createTarget(cooledReplaceables, ModBlocks.COPPER_SLAG_COOLED_BLOCK.getDefaultState()));
        List<OreFeatureConfig.Target> worldIronSlags =
                List.of(OreFeatureConfig.createTarget(ironSlagReplaceables, ModBlocks.IRON_SLAG_BLOCK.getDefaultState()),
                        OreFeatureConfig.createTarget(cooledReplaceables, ModBlocks.IRON_SLAG_COOLED_BLOCK.getDefaultState())
                        );
        List<OreFeatureConfig.Target> worldGoldSlags =
                List.of(OreFeatureConfig.createTarget(goldSlagReplaceables, ModBlocks.GOLD_SLAG_BLOCK.getDefaultState()),
                        OreFeatureConfig.createTarget(cooledReplaceables, ModBlocks.GOLD_SLAG_COOLED_BLOCK.getDefaultState()));

        register(context, COPPER_SLAG, Feature.ORE, new OreFeatureConfig(worldCopperSlags, 6));
        register(context, IRON_SLAG, Feature.ORE, new OreFeatureConfig(worldIronSlags, 3));
        register(context, GOLD_SLAG, Feature.ORE, new OreFeatureConfig(worldGoldSlags, 4));
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Mineralogy.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
