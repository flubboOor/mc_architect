package de.funky_clan.mc.net.packets;

import de.funky_clan.mc.net.BasePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * @author synopia
 */
public class ChunkData extends BasePacket {

    public static final int ID = 0x33;
    private int x;
    private int y;
    private int z;
    private int sizeX;
    private int sizeY;
    private int sizeZ;
    private byte[] data;

    @Override
    public int getPacketId() {
        return ID;
    }

    @Override
    public void decode(DataInputStream in) throws IOException {
        Inflater inflater = new Inflater();
        byte[] compressedData;
        x = in.readInt();
        y = in.readShort();
        z = in.readInt();
        sizeX = in.read() + 1;
        sizeY = in.read() + 1;
        sizeZ = in.read() + 1;
        int compressedSize = in.readInt();
        compressedData = new byte[compressedSize];
        in.readFully(compressedData);
        int uncompressedSize = (sizeX * sizeY * sizeZ * 5) / 2;
        data = new byte[uncompressedSize];
        inflater.reset();
        inflater.setInput(compressedData);
        try {
            inflater.inflate(data, 0, uncompressedSize);
        } catch (DataFormatException dataformatexception) {
            throw new IOException("Bad compressed data format");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public void encode(DataOutputStream out) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
