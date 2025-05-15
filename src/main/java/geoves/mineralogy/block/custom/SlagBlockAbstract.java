package geoves.mineralogy.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SlagBlockAbstract extends Block {
    public SlagBlockAbstract(Settings settings) {
        super(settings);
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity)
            if (entity.getWorld() instanceof ServerWorld serverWorld) {
                entity.damage(serverWorld, world.getDamageSources().hotFloor(), 1.0F);
            }

        super.onSteppedOn(world, pos, state, entity);
    }
}
