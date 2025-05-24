package geoves.mineralogy.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import geoves.mineralogy.Mineralogy;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CoolerScreen extends HandledScreen<CoolerScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(Mineralogy.MOD_ID, "textures/gui/cooler_block_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(Mineralogy.MOD_ID, "textures/gui/arrow_progress.png");
    private static final Identifier COOLANT_PROGRESS =
            Identifier.of(Mineralogy.MOD_ID, "textures/gui/cold_fuel_meter.png");

    public CoolerScreen(CoolerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}