package me.cortex.voxy.client.mixin.joml;

import org.joml.FrustumIntersection;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

@Mixin(value = FrustumIntersection.class, remap = false)
public interface AccessFrustumIntersection {
    @Accessor Vector4f[] getPlanes();

}
