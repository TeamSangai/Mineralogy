package geoves.mineralogy.datagen;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_SLAG_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_SLAG_COOLED_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_SLAG_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_SLAG_COOLED_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_SLAG_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_SLAG_COOLED_BLOCK);
    blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PAYDIRT_DRY);

    blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FREEZER_BLOCK);
    blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SLAG_FURNACE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COPPER_NUGGET, Models.GENERATED);
    }
}
