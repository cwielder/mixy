package me.cortex.voxy;

import me.cortex.voxy.client.config.VoxyConfigScreenFactory;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;

@Mod(MixyStub.MODID)
public class MixyStub {

    public static final String MODID = "mixy";

    public MixyStub(ModContainer container) {
        container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(VoxyConfigScreenFactory.getModConfigScreenFactory()));
    }
}
