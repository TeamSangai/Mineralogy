package geoves.mineralogy.datagen;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.IRON_SLAG_BLOCK).add(ModBlocks.IRON_SLAG_COOLED_BLOCK)
                .add(ModBlocks.COPPER_SLAG_BLOCK).add(ModBlocks.COPPER_SLAG_COOLED_BLOCK)
                .add(ModBlocks.GOLD_SLAG_BLOCK).add(ModBlocks.GOLD_SLAG_COOLED_BLOCK)
                        .add(ModBlocks.PAYDIRT_DRY);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.GOLD_SLAG_BLOCK).add(ModBlocks.GOLD_SLAG_COOLED_BLOCK)
                .add(ModBlocks.IRON_SLAG_BLOCK).add(ModBlocks.IRON_SLAG_COOLED_BLOCK)
                .add(ModBlocks.COPPER_SLAG_BLOCK).add(ModBlocks.COPPER_SLAG_COOLED_BLOCK);
    }
}
