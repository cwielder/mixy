package me.cortex.voxy.client.mixin.sodium;

import me.cortex.voxy.client.core.IGetVoxelCore;
<<<<<<< HEAD
=======
import me.cortex.voxy.client.core.util.IrisUtil;
import me.jellysquid.mods.sodium.client.gl.device.CommandList;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import me.jellysquid.mods.sodium.client.render.chunk.DefaultChunkRenderer;
import me.jellysquid.mods.sodium.client.render.chunk.lists.ChunkRenderListIterable;
import me.jellysquid.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
import me.jellysquid.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
import me.jellysquid.mods.sodium.client.render.viewport.CameraTransform;
>>>>>>> 2e9a2468886dd84d09134d7aebf1b792995d500a
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.embeddedt.embeddium.impl.gl.device.CommandList;
import org.embeddedt.embeddium.impl.render.chunk.ChunkRenderMatrices;
import org.embeddedt.embeddium.impl.render.chunk.DefaultChunkRenderer;
import org.embeddedt.embeddium.impl.render.chunk.lists.ChunkRenderListIterable;
import org.embeddedt.embeddium.impl.render.chunk.terrain.DefaultTerrainRenderPasses;
import org.embeddedt.embeddium.impl.render.chunk.terrain.TerrainRenderPass;
import org.embeddedt.embeddium.impl.render.viewport.CameraTransform;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DefaultChunkRenderer.class, remap = false)
public class MixinDefaultChunkRenderer {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/embeddedt/embeddium/impl/render/chunk/ShaderChunkRenderer;end(Lorg/embeddedt/embeddium/impl/render/chunk/terrain/TerrainRenderPass;)V", shift = At.Shift.BEFORE))
    private void injectRender(ChunkRenderMatrices matrices, CommandList commandList, ChunkRenderListIterable renderLists, TerrainRenderPass renderPass, CameraTransform camera, CallbackInfo ci) {
        if (renderPass == DefaultTerrainRenderPasses.CUTOUT) {
            var core = ((IGetVoxelCore) MinecraftClient.getInstance().worldRenderer).getVoxelCore();
            if (core != null) {
                var stack = new MatrixStack();
                stack.loadIdentity();
                stack.multiplyPositionMatrix(new Matrix4f(matrices.modelView()));
                core.renderOpaque(stack, camera.x, camera.y, camera.z);
            }
        }
    }
}
