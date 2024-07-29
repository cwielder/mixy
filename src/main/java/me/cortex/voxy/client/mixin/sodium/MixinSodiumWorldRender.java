package me.cortex.voxy.client.mixin.sodium;

import net.minecraft.client.render.RenderLayer;
import org.embeddedt.embeddium.impl.render.EmbeddiumWorldRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EmbeddiumWorldRenderer.class, remap = false)
public class MixinSodiumWorldRender {
    @Inject(method = "drawChunkLayer", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelRender(RenderLayer renderLayer, Matrix4f normal, double x, double y, double z, CallbackInfo ci) {
        //ci.cancel();
    }
}
