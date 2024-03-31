package me.cortex.voxy;

import me.cortex.voxy.client.config.VoxyConfigScreenFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = "mixy")
public class ClientEvents {

    @SubscribeEvent
    static void registerConfigScreen(FMLConstructModEvent event) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(VoxyConfigScreenFactory.getModConfigScreenFactory()));
    }
}
