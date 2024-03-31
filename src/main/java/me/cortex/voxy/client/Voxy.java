package me.cortex.voxy.client;

import me.cortex.voxy.MixyStub;
import me.cortex.voxy.client.config.VoxyConfigScreenFactory;
import me.cortex.voxy.client.core.VoxelCore;
import me.cortex.voxy.client.saver.ContextSelectionSystem;
import me.cortex.voxy.client.terrain.WorldImportCommand;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Voxy {
    public static final String VERSION;

    static {
        VERSION = ModList.get().getModFileById(MixyStub.MODID).versionString();
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
