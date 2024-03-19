package me.cortex.voxy.client.core.util;

import net.coderbot.iris.pipeline.ShadowRenderer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;

public class IrisUtil {
    private static final boolean IRIS_INSTALLED = LoadingModList.get().getModFileById("iris") != null;


    private static boolean irisShadowActive0() {
        return ShadowRenderer.ACTIVE;
    }

    public static boolean irisShadowActive() {
        return IRIS_INSTALLED && irisShadowActive0();
    }
}
