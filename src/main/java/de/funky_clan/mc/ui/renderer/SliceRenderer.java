package de.funky_clan.mc.ui.renderer;

import de.funky_clan.mc.config.DataValues;
import de.funky_clan.mc.math.Point2d;
import de.funky_clan.mc.math.Point2i;
import de.funky_clan.mc.model.PixelType;
import de.funky_clan.mc.model.RenderContext;
import de.funky_clan.mc.model.Slice;

import java.awt.*;

/**
 * @author synopia
 */
public class SliceRenderer implements Renderer<Slice> {
    @Override
    public void render(Slice slice, RenderContext c) {
        Graphics2D g  = c.getGraphics();
        int        sx = c.getWindowStart().x() - 1;
        int        sy = c.getWindowStart().y() - 1;
        int        ex = c.getWindowEnd().x() + 2;
        int        ey = c.getWindowEnd().y() + 2;

        for( int y = sy; y < ey; y++ ) {
            for( int x = sx; x < ex; x++ ) {
                Point2i position = new Point2i(x, y);
                int blockId = slice.getPixel(position, PixelType.BLOCK_ID);
                int blueprint = slice.getPixel(position, PixelType.BLUEPRINT);

                Color colorForBlock = null;
                double alpha = 1;
                if( blockId==0 ) {
                    int currentSlice = slice.getSlice();
                    for( int i=currentSlice-1; i>currentSlice-20; i--) {
                        blockId = slice.getPixel(position, i, PixelType.BLOCK_ID );
                        if( blockId!=0 ) {
                            alpha = 1-(currentSlice-i)/20.;
                            break;
                        }
                    }
                }

                if( blockId > 0 ) {
                    colorForBlock = c.getColors().getColorForBlock(blockId);
                } else {
                }
                if( blueprint==1 ) {
                    if( colorForBlock==null ) {
                        colorForBlock = c.getColors().getColorForBlock(DataValues.AIR.getId());
                    }
                    colorForBlock = colorForBlock.darker().darker().darker();
                }

                if( colorForBlock!=null ) {
                    colorForBlock = new Color((int)(alpha*colorForBlock.getRed()), (int)(alpha*colorForBlock.getGreen()), (int)(alpha*colorForBlock.getBlue()), colorForBlock.getAlpha() );
                    g.setColor(colorForBlock);
                    Point2i curr = c.sliceToScreen(position.toPoint2d());
                    Point2i size = c.screenUnit(position.toPoint2d());

                    g.fillRect( curr.x(), curr.y(), size.x(), size.y());
                }
            }
        }

    }
}
