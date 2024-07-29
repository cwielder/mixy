package me.cortex.voxy;

import me.cortex.voxy.client.config.VoxyConfigScreenFactory;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
<<<<<<< HEAD
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
=======
import net.neoforged.neoforge.client.ConfigScreenHandler;
>>>>>>> 2e9a2468886dd84d09134d7aebf1b792995d500a

@Mod(MixyStub.MODID)
public class MixyStub {

    public static final String MODID = "mixy";

    public MixyStub(ModContainer container) {
<<<<<<< HEAD
        container.registerExtensionPoint(IConfigScreenFactory.class, VoxyConfigScreenFactory.getModConfigScreenFactory());
=======
        container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(VoxyConfigScreenFactory.getModConfigScreenFactory()));
>>>>>>> 2e9a2468886dd84d09134d7aebf1b792995d500a
    }
}
