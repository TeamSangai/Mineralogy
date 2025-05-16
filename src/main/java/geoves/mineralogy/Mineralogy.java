package geoves.mineralogy;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.block.entity.ModBlockEntities;
import geoves.mineralogy.item.ModItems;
import geoves.mineralogy.recipe.ModRecipes;
import geoves.mineralogy.screen.ModScreenHandlers;
import geoves.mineralogy.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static geoves.mineralogy.world.ModPlacedFeatures.*;

public class Mineralogy implements ModInitializer {
	public static final String MOD_ID = "mineralogy";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipies();
		ModWorldGeneration.generateModWorldGen();
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, COPPER_SLAG_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, IRON_SLAG_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, GOLD_SLAG_PLACED_KEY);
		LOGGER.info("Minerals!!");
	}
}