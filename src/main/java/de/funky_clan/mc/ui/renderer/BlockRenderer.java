package de.funky_clan.mc.ui.renderer;

import de.funky_clan.mc.model.RenderContext;
import de.funky_clan.mc.model.SelectedBlock;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author synopia
 */
public class BlockRenderer<B extends SelectedBlock> extends BaseRenderer<B> {
    @Override
    public void render( B object, RenderContext c ) {
        Graphics2D g = c.getGraphics();
        Color      color;

        if( object.getColor() == null ) {
            color = c.getColors().getSelectedBlockColor();
        } else {
            color = object.getColor();
        }

        renderBox( c, object.getPositionX(), object.getPositionY(), object.getPositionZ(),
                   object.getPositionX() + object.getSizeX(), object.getPositionY() + object.getHeight(),
                   object.getPositionZ() + object.getSizeY(), color, true,
                   object.getType() == SelectedBlock.Type.CENTERED, false );
    }
}
