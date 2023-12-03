package com.flystonedev.abstracts.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ResizeImage {

    public static byte[] resizeImage(MultipartFile originalImage) throws IOException {
        Integer targetWidth = 300;
        Integer targetHeight = 300;
        BufferedImage bufferedImage = ImageIO.read(originalImage.getInputStream());
        if(bufferedImage.getHeight()>bufferedImage.getWidth()){
            targetHeight = 300 * bufferedImage.getHeight()/bufferedImage.getWidth();
        } else  {
            targetWidth = 300 * bufferedImage.getWidth()/bufferedImage.getHeight();
        }
        Image resultingImage = ImageIO.read(originalImage.getInputStream()).getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        ImageIO.write(outputImage,"jpg",data);

        return data.toByteArray();
    }
}
