package geoves.mineralogy.screen.custom;

import geoves.mineralogy.Mineralogy;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SlagFurnaceScreen extends HandledScreen<SlagFurnaceScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(Mineralogy.MOD_ID, "textures/gui/slag_furnace_block_gui.png");
    private static final Identifier LIT_PROGRESS_TEXTURE = Identifier.ofVanilla("container/furnace/lit_progress");
    private static final Identifier BURN_PROGRESS_TEXTURE = Identifier.ofVanilla("container/blast_furnace/burn_progress");

    private final Identifier litProgressTexture = LIT_PROGRESS_TEXTURE;
    private final Identifier burnProgressTexture = BURN_PROGRESS_TEXTURE;

    public SlagFurnaceScreen(SlagFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;
        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        if (this.handler.isBurning()) {
            int k = 14;
            int l = MathHelper.ceil(this.handler.getFuelProgress() * 22.0F) + 1;
            context.drawGuiTexture(RenderLayer::getGuiTextured, this.burnProgressTexture, 24, 16, 0, 0, i + 79, j + 34, l, 16);
        }

        int k = 24;
        int l = MathHelper.ceil(this.handler.getCookProgress() * 24.0F);
        context.drawGuiTexture(RenderLayer::getGuiTextured, this.litProgressTexture, 14, 14, 0, 14 - l, i + 56, j + 36 + 14 - l, 14, l);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}