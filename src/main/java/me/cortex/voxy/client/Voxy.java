package me.cortex.voxy.client;

import me.cortex.voxy.client.core.VoxelCore;
import me.cortex.voxy.client.saver.ContextSelectionSystem;
import me.cortex.voxy.client.terrain.WorldImportCommand;
import me.cortex.voxy.common.config.Serialization;
import me.cortex.voxy.common.storage.compressors.ZSTDCompressor;
import me.cortex.voxy.common.storage.config.StorageConfig;
import net.minecraft.client.world.ClientWorld;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Voxy {
    public static final String VERSION;

    static {
        VERSION = ModList.get().getModFileById("voxy").versionString();
    }

    @SubscribeEvent
    static void onInitializeClient(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(WorldImportCommand.register());
    }


    private static final ContextSelectionSystem selector = new ContextSelectionSystem();

    public static VoxelCore createVoxelCore(ClientWorld world) {
        var selection = selector.getBestSelectionOrCreate(world);
        return new VoxelCore(selection);
    }
}
