package com.medialab.rental.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.ImageReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarcodeScanner {
    public static String decodeBarcode(File barcodeFile) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(barcodeFile);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }
}

/**
 * Decodeert een barccode van een gegeven foto
 *
 * @param barcodeFile The foto van de barcode
 * @return Geeft data terug van wat erop staat
 */
