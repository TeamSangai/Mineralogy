package geoves.mineralogy.block.custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PaydirtMoist extends Block {
    public PaydirtMoist(Settings settings) {
        super(settings);
    }
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 BlockHitResult hit) {
        ItemStack stack = new ItemStack(Items.IRON_NUGGET);
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        return ActionResult.SUCCESS;
    }


}
