package geoves.mineralogy.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import geoves.mineralogy.Mineralogy;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public record SlagCoolingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<SlagCoolingRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
    DefaultedList<Ingredient> list = DefaultedList.of();
    list.add(this.inputItem);
    return list;
    }

    @Override
    public boolean matches(SlagCoolingRecipeInput input, World world) {
        if (world.isClient()){
            return false;
        }
        return inputItem.test(input.getStackInSlot(1));
    }

    @Override
    @NotNull
    public ItemStack craft(SlagCoolingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }


    @Override
    public RecipeSerializer<? extends Recipe<SlagCoolingRecipeInput>> getSerializer() {
        return ModRecipes.SLAG_COOLING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SlagCoolingRecipeInput>> getType() {
        return ModRecipes.SLAG_COOLING_RECIPE_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(this.inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return Registry.register(
                Registries.RECIPE_BOOK_CATEGORY,
                Identifier.of(Mineralogy.MOD_ID, "recipes"),
                new RecipeBookCategory()
        );
    }
    public static class Serializer implements RecipeSerializer<SlagCoolingRecipe> {
        public static final MapCodec<SlagCoolingRecipe> CODEC = RecordCodecBuilder.mapCodec(slagCoolingRecipeInstance -> slagCoolingRecipeInstance.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(SlagCoolingRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(SlagCoolingRecipe::output)).apply(slagCoolingRecipeInstance, SlagCoolingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, SlagCoolingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, SlagCoolingRecipe::inputItem,
                        ItemStack.PACKET_CODEC, SlagCoolingRecipe::output,
                        SlagCoolingRecipe::new);

        @Override
        public MapCodec<SlagCoolingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SlagCoolingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
