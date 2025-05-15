package geoves.mineralogy.mixin;


import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

@Mixin(PowderSnowBucketItem.class)
public class SnowBucketCraftingRemainingFix extends BlockItem implements FluidModificationItem
{
        @Mutable
        @Final
        @Shadow
        private final SoundEvent placeSound;

        public SnowBucketCraftingRemainingFix(Block block, SoundEvent placeSound, Item.Settings settings) {
                super(block, settings.recipeRemainder(Items.BUCKET));
                this.placeSound = placeSound;
        }

        @Override
        public boolean placeFluid(@Nullable LivingEntity user, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
                if (world.isInBuildLimit(pos) && world.isAir(pos)) {
                        if (!world.isClient) {
                                world.setBlockState(pos, this.getBlock().getDefaultState(), 3);
                        }

                        world.emitGameEvent(user, GameEvent.FLUID_PLACE, pos);
                        world.playSound(user, pos, this.placeSound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return true;
                } else {
                        return false;
                }
        }
}
