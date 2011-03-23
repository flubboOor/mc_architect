package de.funky_clan.mc.net.packets;

import de.funky_clan.mc.net.BasePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author synopia
 */
public class EntityTeleport extends BasePacket {
    public static final int ID = 0x22;
    private int eid;
    private int x;
    private int y;
    private int z;
    private int yaw;
    private int pitch;

    @Override
    public int getPacketId() {
        return ID;
    }

    @Override
    public void decode(DataInputStream in) throws IOException {
        eid = in.readInt();
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        yaw = in.readByte();
        pitch = in.readByte();
    }

    @Override
    public void encode(DataOutputStream out) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getEid() {
        return eid;
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

    public int getYaw() {
        return yaw;
    }

    public int getPitch() {
        return pitch;
    }
}
