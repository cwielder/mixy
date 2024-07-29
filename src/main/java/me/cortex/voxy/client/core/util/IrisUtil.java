package me.cortex.voxy.client.core.util;

import net.irisshaders.iris.shadows.ShadowRenderer;
<<<<<<< HEAD
import org.spongepowered.asm.mixin.Unique;
=======
>>>>>>> 2e9a2468886dd84d09134d7aebf1b792995d500a
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;

public class IrisUtil {
    private static final boolean IRIS_INSTALLED = LoadingModList.get().getModFileById("iris") != null || LoadingModList.get().getModFileById("oculus") != null;


    private static boolean irisShadowActive0() {
        return ShadowRenderer.ACTIVE;
    }

    public static boolean irisShadowActive() {
        return IRIS_INSTALLED && irisShadowActive0();
    }
}
