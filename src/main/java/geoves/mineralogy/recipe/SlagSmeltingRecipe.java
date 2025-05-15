package geoves.mineralogy.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public record SlagSmeltingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<SlagSmeltingRecipeInput> {

    @Override
    public boolean matches(SlagSmeltingRecipeInput input, World world) {
        return false;
    }

    @Override
    public ItemStack craft(SlagSmeltingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return null;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SlagSmeltingRecipeInput>> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<SlagSmeltingRecipeInput>> getType() {
        return null;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return null;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }
}
