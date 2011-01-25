package de.funky_clan.mc.config;

import de.funky_clan.mc.model.BackgroundImage;
import de.funky_clan.mc.model.Graphics;
import de.funky_clan.mc.model.Model;

import java.util.HashMap;

/**
 * @author synopia
 */
public class Configuration {
    private Model model;
    private Graphics graphics;
    private HashMap<String, BackgroundImage> images = new HashMap<String, BackgroundImage>();

    public Model getModel() {
        return model;
    }

    public Configuration() {

    }

    public Configuration create(int width, int height, int slices) {
        model = new Model(width, height, slices);
        graphics = new Graphics(model);

        return this;
    }

    public Configuration drawEllipse(int x, int y, int width, int height) {
        for (int slice = 0; slice < model.getSlices(); slice++) {
            drawEllipse(x, y, width, height, slice);
        }

        return this;
    }

    public Configuration drawEllipse(int x, int y, int width, int height, int slice) {
        graphics.ellipse(x, y, width, height, slice, 1);

        return this;
    }

    public Configuration image(String filename) {
        image(filename, 0, model.getSlices());

        return this;
    }

    public Configuration image(String filename, int sliceFrom, int sliceTo) {
        for (int i = sliceFrom; i <= sliceTo; i++) {
            image(filename, i);
        }

        return this;
    }

    public Configuration image(String filename, int slice) {
        BackgroundImage image = images.get(filename);
        if (image == null) {
            image = new BackgroundImage(filename);
            images.put(filename, image);
        }
        model.getSlice(slice).setImage(image);

        return this;
    }

    public Configuration axis(int x, int y) {
        graphics.hLine(0, y, model.getWidth(), 2);
        graphics.vLine(x, 0, model.getHeight(), 2);

        return this;
    }
}
