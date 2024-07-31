package me.cortex.voxy.client.mixin.joml;

import org.joml.FrustumIntersection;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

//@Mixin(value = FrustumIntersection.class, remap = false)
public class AccessFrustumIntersection {
    //@Accessor Vector4f[] getPlanes();
    private static final VarHandle planesHandle;
    static {
        Field planesField = null;
        try {
            planesField = FrustumIntersection.class.getDeclaredField("planes");
            planesField.setAccessible(true);
            planesHandle = MethodHandles.lookup().unreflectVarHandle(planesField);
            planesField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public static Vector4f[] getPlanes(FrustumIntersection fi) {
        return (Vector4f[]) planesHandle.get(fi);
    }
}
