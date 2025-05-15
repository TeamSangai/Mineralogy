package geoves.mineralogy.datagen;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup,recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.MISC, Items.COPPER_INGOT)
                        .pattern("CCC")
                        .pattern("CCC")
                        .pattern("CCC")
                        .input('C', ModItems.COPPER_NUGGET)
                        .criterion(hasItem(ModItems.COPPER_NUGGET),  conditionsFromItem(ModItems.COPPER_NUGGET))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.COPPER_PAN)
                        .pattern("C C")
                        .pattern(" C ")
                        .input('C', Items.COPPER_INGOT)
                        .criterion(hasItem(Items.COPPER_INGOT),  conditionsFromItem(Items.COPPER_INGOT))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.COPPER_SLAG_COOLED_BLOCK)
                        .input(ModBlocks.COPPER_SLAG_BLOCK)
                        .input(Items.POWDER_SNOW_BUCKET)
                        .criterion(hasItem(ModBlocks.COPPER_SLAG_BLOCK),  conditionsFromItem(ModBlocks.COPPER_SLAG_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.IRON_SLAG_COOLED_BLOCK)
                        .input(ModBlocks.IRON_SLAG_BLOCK)
                        .input(Items.POWDER_SNOW_BUCKET)
                        .criterion(hasItem(ModBlocks.IRON_SLAG_BLOCK),  conditionsFromItem(ModBlocks.IRON_SLAG_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.GOLD_SLAG_COOLED_BLOCK)
                        .input(ModBlocks.GOLD_SLAG_BLOCK)
                        .input(Items.POWDER_SNOW_BUCKET)
                        .criterion(hasItem(ModBlocks.GOLD_SLAG_BLOCK),  conditionsFromItem(ModBlocks.GOLD_SLAG_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModItems.COPPER_NUGGET, 9)
                        .input(Items.COPPER_INGOT)
                        .criterion(hasItem(Items.COPPER_INGOT),  conditionsFromItem(Items.COPPER_INGOT))
                        .offerTo(exporter);


            }
        };
    }
    @Override
    public String getName() {
        return "";
    }
}




