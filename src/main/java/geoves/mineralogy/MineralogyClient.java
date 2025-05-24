package geoves.mineralogy;

import geoves.mineralogy.screen.ModScreenHandlers;
import geoves.mineralogy.screen.custom.CoolerScreen;
import geoves.mineralogy.screen.custom.FreezerScreen;
import geoves.mineralogy.screen.custom.SlagFurnaceScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class MineralogyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.FREEZER_SCREEN_HANDLER, FreezerScreen::new);
        HandledScreens.register(ModScreenHandlers.SLAG_FURNACE_SCREEN_HANDLER, SlagFurnaceScreen::new);
        HandledScreens.register(ModScreenHandlers.COOLER_SCREEN_HANDLER, CoolerScreen::new);
    }
}
