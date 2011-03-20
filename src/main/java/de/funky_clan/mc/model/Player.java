package de.funky_clan.mc.model;

//~--- JDK imports ------------------------------------------------------------

/**
 * @author paul.fritsche@googlemail.com
 */
public class Player extends SelectedBlock {
    private int     direction;
    private boolean drawViewCone;
    private int     z;

    public void setDrawViewCone(boolean drawViewCone) {
        this.drawViewCone = drawViewCone;
    }

    public boolean isDrawViewCone() {
        return drawViewCone;
    }

    public int getZ() {
        return z;
    }

    public void setZ( int z ) {
        this.z = z;
    }

    @Override
    public Type getType() {
        return SelectedBlock.Type.CENTERED;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection( int direction ) {
        this.direction = direction+90;
    }

    @Override
    public double getHeight() {
        return 1.7;
    }

    @Override
    public double getSizeX() {
        return 0.7;
    }

    @Override
    public double getSizeY() {
        return 0.7;
    }

}
