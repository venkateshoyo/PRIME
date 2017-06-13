package com.PRIME.javafx3D;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;


import java.awt.image.BufferedImage;

/**
 * Created by hkban on 6/13/2017.
 */
class MyTranscoder extends ImageTranscoder {

    private BufferedImage image = null;

    @Override
    public BufferedImage createImage(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        return image;
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
    }

    public BufferedImage getImage() {
        return image;
    }
}
