package me.cortex.voxy;

import me.cortex.voxy.client.config.VoxyConfigScreenFactory;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;

@Mod("voxy")
public class VoxyStub {
    public VoxyStub(ModContainer container) {
        container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(VoxyConfigScreenFactory.getModConfigScreenFactory()));
    }
}
