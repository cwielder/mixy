package me.cortex.voxelmon.core.world;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//Represents a loaded world section at a specific detail level
// holds a 32x32x32 region of detail
public final class WorldSection {
    public final int lvl;
    public final int x;
    public final int y;
    public final int z;

    long[] data;
    private final ActiveSectionTracker tracker;
    public final AtomicBoolean inSaveQueue = new AtomicBoolean();

    //When the first bit is set it means its loaded
    private final AtomicInteger atomicState = new AtomicInteger(1);

    WorldSection(int lvl, int x, int y, int z, ActiveSectionTracker tracker) {
        this.lvl = lvl;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tracker = tracker;

        this.data = new long[32*32*32];
    }

    @Override
    public int hashCode() {
        return ((x*1235641+y)*8127451+z)*918267913+lvl;
    }


    public int acquire() {
        int state = this.atomicState.addAndGet(2);
        if ((state&1) == 0) {
            throw new IllegalStateException("Tried to acquire unloaded section");
        }
        return state>>1;
    }

    public int release() {
        int state = this.atomicState.addAndGet(-2);
        if (state < 1) {
            throw new IllegalStateException("Section got into an invalid state");
        }
        if ((state&1)==0) {
            throw new IllegalStateException("Tried releasing a freed section");
        }
        if ((state>>1)==0) {
            this.tracker.tryUnload(this);
        }

        return state>>1;
    }

    //Returns true on success, false on failure
    boolean trySetFreed() {
        int witness = this.atomicState.compareAndExchange(1, 0);
        if ((witness&1)==0 && witness != 0) {
            throw new IllegalStateException("Section marked as free but has refs");
        }
        boolean isFreed = witness == 1;
        if (isFreed) {
            this.data = null;
        }
        return isFreed;
    }

    public void assertNotFree() {
        if ((this.atomicState.get() & 1) == 0) {
            throw new IllegalStateException();
        }
    }

    public long getKey() {
        return WorldEngine.getWorldSectionId(this.lvl, this.x, this.y, this.z);
    }

    public static int getIndex(int x, int y, int z) {
        int M = (1<<5)-1;
        if (x<0||x>M||y<0||y>M||z<0||z>M) {
            throw new IllegalArgumentException("Out of bounds: " + x + ", " + y + ", " + z);
        }
        return ((y&M)<<10)|((z&M)<<5)|(x&M);
    }

    public long set(int x, int y, int z, long id) {
        int idx = getIndex(x,y,z);
        long old = this.data[idx];
        this.data[idx] = id;
        return old;
    }

    //Generates a copy of the data array, this is to help with atomic operations like rendering
    public long[] copyData() {
        this.assertNotFree();
        return Arrays.copyOf(this.data, this.data.length);
    }

    public boolean tryAcquire() {
        int state = this.atomicState.updateAndGet(val -> {
            if ((val&1) != 0) {
                return val+2;
            }
            return val;
        });
        return (state&1) != 0;
    }
}

//TODO: for serialization, make a huffman encoding tree on the integers since that should be very very efficent for compression
