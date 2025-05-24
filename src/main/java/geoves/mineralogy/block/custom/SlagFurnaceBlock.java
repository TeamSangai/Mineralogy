package geoves.mineralogy.block.custom;

import com.mojang.serialization.MapCodec;
import geoves.mineralogy.block.entity.ModBlockEntities;
import geoves.mineralogy.block.entity.custom.FreezerBlockEntity;
import geoves.mineralogy.block.entity.custom.SlagFurnaceBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SlagFurnaceBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public static final MapCodec<SlagFurnaceBlock> CODEC = createCodec(SlagFurnaceBlock::new);
    private static final Logger log = LoggerFactory.getLogger(SlagFurnaceBlock.class);

    public SlagFurnaceBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(LIT, false));

    }

    public MapCodec<SlagFurnaceBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                         PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            log.info("slag here");
            NamedScreenHandlerFactory screenHandlerFactory = ((SlagFurnaceBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {

                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }


    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (world instanceof ServerWorld){
            if(state.getBlock() != newState.getBlock()) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if(blockEntity instanceof SlagFurnaceBlockEntity) {
                    ItemScatterer.spawn(world, pos, ((SlagFurnaceBlockEntity) blockEntity));
                    world.updateComparators(pos, this);
                }
                super.onStateReplaced(state, (ServerWorld) world, pos, moved);
            }
        }
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockRenderView renderView, BlockPos pos, Direction side, @Nullable BlockState sourceState, @Nullable BlockPos sourcePos) {
        return super.getAppearance(state, renderView, pos, side, sourceState, sourcePos);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SlagFurnaceBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if(world.isClient()) {
            return null;
        }

        return validateTicker(type, ModBlockEntities.SLAG_FURNACE_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(LIT);
    }

}
