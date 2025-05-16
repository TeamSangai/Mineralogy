package geoves.mineralogy.block.entity.custom;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.block.entity.ImplementedInventory;
import geoves.mineralogy.block.entity.ModBlockEntities;
import geoves.mineralogy.recipe.ModRecipes;
import geoves.mineralogy.recipe.SlagSmeltingRecipe;
import geoves.mineralogy.recipe.SlagSmeltingRecipeInput;
import geoves.mineralogy.screen.custom.FreezerScreenHandler;
import geoves.mineralogy.screen.custom.SlagFurnaceScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SlagFurnaceBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int OUTPUT_SLOT_TWO = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 230;

    public SlagFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SLAG_FURNACE_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SlagFurnaceBlockEntity.this.progress;
                    case 1 -> SlagFurnaceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: SlagFurnaceBlockEntity.this.progress = value;
                    case 1: SlagFurnaceBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.mineralogy.slag_furnace_block");
    }
    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("slag_furnace.progress", progress);
        nbt.putInt("slag_furnace.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("slag_furnace.progress", progress);
        maxProgress = nbt.getInt("slag_furnace.max_progress", maxProgress);
        super.readNbt(nbt, registryLookup);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SlagFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }
    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 230;
    }
    private void craftItem() {
        Optional<RecipeEntry<SlagSmeltingRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output();
        ItemStack output_two = recipe.get().value().byproduct_output();

        this.removeStack(INPUT_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
        this.setStack(OUTPUT_SLOT_TWO, new ItemStack(output_two.getItem(),
                this.getStack(OUTPUT_SLOT_TWO).getCount() + output_two.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<SlagSmeltingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().output();
        ItemStack output_two = recipe.get().value().byproduct_output();

        return canInsertAmountIntoOutputSlots(output.getCount(), output_two.getCount()) && canInsertItemIntoOutputSlots(output, output_two);
    }

    private Optional<RecipeEntry<SlagSmeltingRecipe>> getCurrentRecipe() {
        ServerRecipeManager.MatchGetter<SlagSmeltingRecipeInput, SlagSmeltingRecipe> getter =
                ServerRecipeManager.createCachedMatchGetter(ModRecipes.SLAG_SMELTING_RECIPE_TYPE);

        return getter.getFirstMatch(new SlagSmeltingRecipeInput(inventory.get(INPUT_SLOT)), (ServerWorld) this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlots(ItemStack output, ItemStack output_two) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT_TWO).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem() || this.getStack(OUTPUT_SLOT_TWO).getItem() == output_two.getItem();
    }

    private boolean canInsertAmountIntoOutputSlots(int count, int count_two) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();
        int maxCount_two = this.getStack(OUTPUT_SLOT_TWO).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT_TWO).getMaxCount();
        int currentCount_two = this.getStack(OUTPUT_SLOT_TWO).getCount();

        return maxCount >= currentCount + count && maxCount_two >= currentCount_two + count_two;
    }

}
