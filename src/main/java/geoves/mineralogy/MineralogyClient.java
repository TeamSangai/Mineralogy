package geoves.mineralogy;

import geoves.mineralogy.block.entity.ModBlockEntities;
import geoves.mineralogy.screen.ModScreenHandlers;
import geoves.mineralogy.screen.custom.FreezerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MineralogyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.FREEZER_SCREEN_HANDLER, FreezerScreen::new);
    }
}
