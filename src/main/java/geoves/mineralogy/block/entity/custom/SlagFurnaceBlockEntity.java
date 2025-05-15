package geoves.mineralogy.block.entity.custom;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.block.entity.ImplementedInventory;
import geoves.mineralogy.block.entity.ModBlockEntities;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SlagFurnaceBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int OUTPUT_SLOT_TWO = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

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
        return null;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return null;
    }

    @Override
    public Text getDisplayName() {
        return null;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return null;
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
        this.maxProgress = 72;
    }
    private void craftItem() {
        ItemStack output = new ItemStack(Items.IRON_INGOT, 1);
        ItemStack output_two = new ItemStack(ModBlocks.IRON_SLAG_BLOCK, 1);

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
        Item input = Items.RAW_IRON;
        ItemStack output = new ItemStack(Items.IRON_INGOT);
        ItemStack output_two = new ItemStack(ModBlocks.IRON_SLAG_BLOCK);

        return this.getStack(INPUT_SLOT).isOf(input) &&
                canInsertAmountIntoOutputSlots(output.getCount(), output_two.getCount()) && canInsertItemIntoOutputSlots(output, output_two);
    }

    private boolean canInsertItemIntoOutputSlots(ItemStack output, ItemStack output_two) {
        return this.getStack(OUTPUT_SLOT).isEmpty() && this.getStack(OUTPUT_SLOT_TWO).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem() && this.getStack(OUTPUT_SLOT_TWO).getItem() == output_two.getItem();
    }

    private boolean canInsertAmountIntoOutputSlots(int count, int count_two) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();
        int maxCount_two = this.getStack(OUTPUT_SLOT_TWO).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT_TWO).getMaxCount();
        int currentCount_two = this.getStack(OUTPUT_SLOT_TWO).getCount();

        return maxCount >= currentCount + count && maxCount_two >= currentCount_two + count_two;
    }
}
