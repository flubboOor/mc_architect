package de.funky_clan.mc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author synopia
 */
public final class Chunk {
    private static final int CHUNK_ARRAY_SIZE = 16 * 16 * 128;
    private final byte map[];
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private final int startX;
    private final int startY;
    private final int startZ;

    private final long id;
    private Logger logger = LoggerFactory.getLogger(Chunk.class);

    public Chunk(final int startX, final int startY, final int startZ, final int sizeX, final int sizeY, final int sizeZ) {
        id = getChunkId(startX>>4, startZ>>4);
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

        map = new byte[CHUNK_ARRAY_SIZE];
    }

    public static long getChunkId(int x, int y) {
        return ((long)x<<32) | (y&0xffff);
    }
    public static long getChunkId(double x, double y) {
        return getChunkId( (int)x>>4, (int)y>>4 );
    }

    public long getId() {
        return id;
    }

    public final int getStartX() {
        return startX;
    }

    public final int getStartY() {
        return startY;
    }

    public final int getStartZ() {
        return startZ;
    }

    public final int getSizeX() {
        return sizeX;
    }

    public final int getSizeY() {
        return sizeY;
    }

    public final int getSizeZ() {
        return sizeZ;
    }

    public final void setPixel(int x, int y, int z, int value) {
        int sy = y - startY;
        int sz = z - startZ;
        int sx = x - startX;
        int index = sy + (sz *sizeY) + (sx *sizeY*sizeZ);
        if( index<0 || index>=CHUNK_ARRAY_SIZE ) {
            throw new IllegalArgumentException(sx+", "+sy+", "+sz+" is no valid chunk pos");
        }
        map[index] = (byte) value;
    }

    public final int getPixel(int x, int y, int z) {
        int sy = y - startY;
        int sz = z - startZ;
        int sx = x - startX;
        int index = sy + (sz *sizeY) + (sx *sizeY*sizeZ);
        if( index<0 || index>=CHUNK_ARRAY_SIZE ) {
            throw new IllegalArgumentException(sx+", "+sy+", "+sz+" is no valid chunk pos");
        }
        return map[index];
    }

    public void updateFullBlock( byte[] data) {
        System.arraycopy(data, 0, map, 0, CHUNK_ARRAY_SIZE);
    }
}
