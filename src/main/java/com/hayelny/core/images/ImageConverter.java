package com.hayelny.core.images;

import com.aspose.imaging.Image;
import com.aspose.imaging.imageoptions.JpegOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageConverter {
    private static final JpegOptions jpegOptions = new JpegOptions();
    public static void convertToJpeg(String path) {
        try(Image image = Image.load(path)) {
            image.save(path, jpegOptions);
        }
    }
}
