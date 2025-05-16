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

public record SlagSmeltingRecipe(Ingredient inputItem, ItemStack output, ItemStack byproduct_output) implements Recipe<SlagSmeltingRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
    DefaultedList<Ingredient> list = DefaultedList.of();
    list.add(this.inputItem);
    return list;
    }

    @Override
    public boolean matches(SlagSmeltingRecipeInput input, World world) {
        if (world.isClient()){
            return false;
        }
        return inputItem.test(input.getStackInSlot(1));
    }

    @Override
    @NotNull
    public ItemStack craft(SlagSmeltingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }


    @Override
    public RecipeSerializer<? extends Recipe<SlagSmeltingRecipeInput>> getSerializer() {
        return ModRecipes.SLAG_SMELTING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SlagSmeltingRecipeInput>> getType() {
        return ModRecipes.SLAG_SMELTING_RECIPE_TYPE;
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
    public static class Serializer implements RecipeSerializer<SlagSmeltingRecipe> {
        public static final MapCodec<SlagSmeltingRecipe> CODEC = RecordCodecBuilder.mapCodec(slagSmeltingRecipeInstance -> slagSmeltingRecipeInstance.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(SlagSmeltingRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(SlagSmeltingRecipe::output),
                ItemStack.CODEC.fieldOf("byproduct_result").forGetter(SlagSmeltingRecipe::byproduct_output)).apply(slagSmeltingRecipeInstance, SlagSmeltingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, SlagSmeltingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, SlagSmeltingRecipe::inputItem,
                        ItemStack.PACKET_CODEC, SlagSmeltingRecipe::output,
                        ItemStack.PACKET_CODEC, SlagSmeltingRecipe::byproduct_output,
                        SlagSmeltingRecipe::new);

        @Override
        public MapCodec<SlagSmeltingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SlagSmeltingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
