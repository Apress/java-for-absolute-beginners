package com.apress.bgn.ch11;

import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.awt.image.MultiResolutionImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MediaDemo {
    private static final Logger log = LoggerFactory.getLogger(MediaDemo.class);

    public static void main(String... args) {
        File src = new File("chapter11/media-handling/src/main/resources/scottish_sky.jpg");
        try {
            log.info(" --- Removing EXIF info ---");
            File destNoExif = new File("chapter11/media-handling/src/main/resources/scottish_sky_noexif.jpg");
            removeExifTag(destNoExif, src);

            log.info(" --- Creating 25% image ---");
            File dest25 = new File("chapter11/media-handling/src/main/resources/scottish_sky_25.jpg");
            resize(dest25, destNoExif, 0.25f);

            log.info(" --- Creating 50% image ---");
            File dest50 = new File("chapter11/media-handling/src/main/resources/scottish_sky_50.jpg");
            resize(dest50, destNoExif, 0.5f);

            log.info(" --- Creating 75% image ---");
            File dest75 = new File("chapter11/media-handling/src/main/resources/scottish_sky_75.jpg");
            resize(dest75, destNoExif, 0.75f);

            Image[] imgList = new Image[]{
                    ImageIO.read(src),// 4032 x 1108
                    ImageIO.read(dest75), // 3024 x 831
                    ImageIO.read(dest25), // 1008 x 277
                    ImageIO.read(dest50) //2016 x 554

            };

            log.info(" --- Creating multi-resolution image ---");
            File destVariant = new File("chapter11/media-handling/src/main/resources/sky_variant.jpg");
            createMultiResImage(destVariant, imgList);

            BufferedImage variantImg = ImageIO.read(destVariant);
            log.info("variant width x height :  {} x {}", variantImg.getWidth(), variantImg.getHeight());
            BufferedImage dest25Img = ImageIO.read(dest25);
            log.info("dest25Img width x height :  {} x {}", dest25Img.getWidth(), dest25Img.getHeight());
            log.info("Are identical? {}", variantImg.equals(dest25Img));
        } catch (Exception e) {
            log.error("Something bad happened.", e);
        }
    }

    private static void removeExifTag(final File dest, final File src) throws Exception {
       new ExifRewriter().removeExifMetadata(src, new FileOutputStream(dest));
    }

    private static void resize(final File dest, final File src, final float percent) throws IOException {
        BufferedImage originalImage = ImageIO.read(src);
        int scaledWidth = (int) (originalImage.getWidth() * percent);
        int scaledHeight = (int) (originalImage.getHeight() * percent);

        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        outputImage.flush();

        ImageIO.write(outputImage, "jpg", dest);
    }

    private static void createMultiResImage(final File dest, final Image[] imgList) throws IOException {
        MultiResolutionImage mrImage = new BaseMultiResolutionImage(0,imgList);
                //new SmartMultiResolutionImage(0, imgList);

        List<Image> variants = mrImage.getResolutionVariants();

        variants.forEach(i -> log.info(i.toString()));

        Image img = mrImage.getResolutionVariant(700, 400);
        log.info("Most fit to the requested size<{},{}>: <{},{}>", 700, 400, img.getWidth(null), img.getHeight(null));

        if (img instanceof BufferedImage) {
            ImageIO.write((BufferedImage) img, "jpg", dest);
        }

    }

}
