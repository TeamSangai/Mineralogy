package geoves.mineralogy.recipe;

import geoves.mineralogy.Mineralogy;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<SlagSmeltingRecipe> SLAG_SMELTING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Mineralogy.MOD_ID, "slag_smelting"),
            new SlagSmeltingRecipe.Serializer());
    public static final RecipeType<SlagSmeltingRecipe> SLAG_SMELTING_RECIPE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Mineralogy.MOD_ID, "slag_smelting"), new RecipeType<SlagSmeltingRecipe>() {
            @Override
            public String toString(){
                return "slag_smelting";
            }});
    public static final RecipeSerializer<SlagCoolingRecipe> SLAG_COOLING_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Mineralogy.MOD_ID, "slag_cooling"),
            new SlagCoolingRecipe.Serializer());
    public static final RecipeType<SlagCoolingRecipe> SLAG_COOLING_RECIPE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Mineralogy.MOD_ID, "slag_cooling"), new RecipeType<SlagCoolingRecipe>() {
                @Override
                public String toString(){
                    return "slag_cooling";
                }});
    public static void registerRecipies() {
        Mineralogy.LOGGER.info("Registering Mod Recipies for " + Mineralogy.MOD_ID);
    }
}
