package me.cortex.voxy.client.terrain;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import me.cortex.voxy.client.core.IGetVoxelCore;
import me.cortex.voxy.client.importers.WorldImporter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;


public class WorldImportCommand {
    public static LiteralArgumentBuilder<ServerCommandSource> register() {
        return CommandManager.literal("voxy").then(
                CommandManager.literal("import")
                        .then(CommandManager.literal("world")
                                .then(CommandManager.argument("world_name", StringArgumentType.string())
                                        .suggests(WorldImportCommand::importWorldSuggester)
                                        .executes(WorldImportCommand::importWorld)))
                        .then(CommandManager.literal("bobby")
                                .then(CommandManager.argument("world_name", StringArgumentType.string())
                                        .executes(WorldImportCommand::importBobby)))
                        .then(CommandManager.literal("raw")
                                .then(CommandManager.argument("path", StringArgumentType.string())
                                        .executes(WorldImportCommand::importRaw))));
    }


    private static int importRaw(CommandContext<ServerCommandSource> ctx) {
        var instance = MinecraftClient.getInstance();
        var file = new File(ctx.getArgument("path", String.class));
        ((IGetVoxelCore)instance.worldRenderer).getVoxelCore().createWorldImporter(MinecraftClient.getInstance().player.clientWorld, file);
        return 0;
    }

    private static int importBobby(CommandContext<ServerCommandSource> ctx) {
        var instance = MinecraftClient.getInstance();
        var file = new File(".bobby").toPath().resolve(ctx.getArgument("world_name", String.class)).toFile();
        ((IGetVoxelCore)instance.worldRenderer).getVoxelCore().createWorldImporter(MinecraftClient.getInstance().player.clientWorld, file);
        return 0;
    }

    private static CompletableFuture<Suggestions> importWorldSuggester(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder sb) {
        try {
            var worlds = Files.list(MinecraftClient.getInstance().runDirectory.toPath().resolve("saves")).toList();
            for (var world : worlds) {
                if (!world.toFile().isDirectory()) {
                    continue;
                }
                var wn = world.getFileName().toString();
                if (CommandSource.shouldSuggest(sb.getRemaining(), wn) || CommandSource.shouldSuggest(sb.getRemaining(), '"'+wn)) {
                    if (wn.contains(" ")) {
                        wn = '"' + wn + '"';
                    }
                    sb.suggest(wn);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.buildFuture();
    }

    private static int importWorld(CommandContext<ServerCommandSource> ctx) {
        var instance = MinecraftClient.getInstance();
        var file = new File("saves").toPath().resolve(ctx.getArgument("world_name", String.class)).resolve("region").toFile();
        ((IGetVoxelCore)instance.worldRenderer).getVoxelCore().createWorldImporter(MinecraftClient.getInstance().player.clientWorld, file);
        return 0;
    }

}