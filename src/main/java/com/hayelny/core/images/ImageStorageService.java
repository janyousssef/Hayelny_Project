package com.hayelny.core.images;

import com.hayelny.core.diagnosis.*;
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
public class ImageStorageService {
    private static final Path IMAGE_DIRECTORY = Path.of(".." + File.separator + "images");
    private final ImageRepo imageRepo;

    public ImageStorageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public String persist(MultipartFile image) {
        String imageId = this.storeImageAsLocalFile(image);
        this.persistInDB(imageId);
        return imageId;
    }

    public byte[] getImageAsBytes(String id) {
        try {
            return Files.readAllBytes(Path.of(".." + File.separator + "images" + File.separator + id + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //this is for testing with AI model
    private static Diagnosis getTestDiagnosis(XrayImage xrayImage) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setImage(xrayImage);
        diagnosis.setConfidence(90D);
        diagnosis.setJudgement(Judgement.POSITIVE);
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);
        return diagnosis;
    }

    private String storeImageAsLocalFile(MultipartFile image) {

        int id;
        try {
            id = Arrays.hashCode(image.getBytes());
            String imagePath = IMAGE_DIRECTORY + File.separator + id + ".jpeg";
            image.transferTo(Path.of(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(id);
    }

    private void persistInDB(String id) {
        XrayImage xrayImage = new XrayImage();
        String imagePath = IMAGE_DIRECTORY + File.separator + id + ".jpeg";
        xrayImage.setImagePath(imagePath);
        xrayImage.setId(id);


        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.execute(() -> {
                try {
                    imageRepo.save(xrayImage);
                } catch (DataIntegrityViolationException e) {
                    log.warn("Attempted to store already existing image with id: {}",
                             id);
                }
            });
        }
    }
}
