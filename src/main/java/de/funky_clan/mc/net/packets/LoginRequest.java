package de.funky_clan.mc.net.packets;

import de.funky_clan.mc.net.BasePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author synopia
 */
public final class LoginRequest extends BasePacket {
    public static final int ID = 0x01;
    private int             dimension;
    private int             entityId;
    private String          password;
    private long            seed;
    private String          username;

    @Override
    public int getPacketId() {
        return ID;
    }

    @Override
    public void decode( DataInputStream in ) throws IOException {
        entityId  = in.readInt();
        username  = in.readUTF();
        password  = in.readUTF();
        seed      = in.readLong();
        dimension = in.readByte();
    }

    @Override
    public void encode( DataOutputStream out ) throws IOException {
        out.writeInt( entityId );
        out.writeUTF( username );
        out.writeUTF( password );
        out.writeLong( seed );
        out.writeByte( dimension );
    }

    public int getEntityId() {
        return entityId;
    }

    public int getProtocolVersion() {
        return entityId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getSeed() {
        return seed;
    }

    public int getDimension() {
        return dimension;
    }
}
