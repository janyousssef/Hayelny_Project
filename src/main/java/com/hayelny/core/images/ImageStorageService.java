package com.hayelny.core.images;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageStorageService {
    private static final Path IMAGE_DIRECTORY = Path.of(".." + File.separator + "images");
    private final ImageRepo imageRepo;
    private final ImageConverter imageConverter;

    public String persist(MultipartFile image) {
        String imageId = this.persistLocally(image);
        this.persistInDB(imageId);
        return imageId;
    }

    public byte[] getImageAsBytes(String id) {
        try {
            return Files.readAllBytes(Path.of(".." + File.separator + "images" + File.separator + id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String persistLocally(MultipartFile image) {

        int id;
        try {
            id = Arrays.hashCode(image.getBytes());
            String imagePath = getImagePath(String.valueOf(id));
            image.transferTo(Path.of(imagePath));
            imageConverter.convertToJpeg(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(id);
    }

    private void persistInDB(String id) {
        XrayImage xrayImage = new XrayImage();
        String imagePath = getImagePath(id);
        xrayImage.setImagePath(imagePath);
        xrayImage.setId(id);

        Runnable uploadToDb = () -> {
            try {
                imageRepo.save(xrayImage);
            } catch (DataIntegrityViolationException e) {
                log.warn("Attempted to store already existing image with id: {}",
                         id);
            }
        };
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.execute(uploadToDb);
        }
    }

    private static String getImagePath(String id) {
        return IMAGE_DIRECTORY + File.separator + id;
    }
}
