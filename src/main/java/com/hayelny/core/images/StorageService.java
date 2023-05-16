package com.hayelny.core.images;

import com.hayelny.core.diagnosis.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@Service
@Slf4j
public class StorageService {
    private static final Path IMAGE_DIRECTORY = Path.of(".." + File.separator + "images");
    private final DiagnosisRepo diagnosisRepo;
    private final ImageRepo imageRepo;

    public StorageService(DiagnosisRepo diagnosisRepo, ImageRepo imageRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.imageRepo = imageRepo;
    }

    private static Diagnosis getTestDiagnosis(XrayImage xrayImage) {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setImage(xrayImage);
        diagnosis.setConfidence(90D);
        diagnosis.setJudgement(Judgement.POSITIVE);
        diagnosis.setDisease(Disease.PNEUMONIA);
        diagnosis.setStatus(DiagnosisStatus.COMPLETED);
        return diagnosis;
    }

    public int storeImageAsLocalFile(MultipartFile image) throws IOException {

        int id = Arrays.hashCode(image.getBytes());
        String imagePath = IMAGE_DIRECTORY + File.separator + id + ".png";
        image.transferTo(Path.of(imagePath));
        return id;
    }

    public void persistInDB(int id) {
        XrayImage xrayImage = new XrayImage();
        String imagePath = IMAGE_DIRECTORY + File.separator + id + ".png";
        xrayImage.setImagePath(imagePath);
        xrayImage.setId((long) id);

        Diagnosis diagnosis = getTestDiagnosis(xrayImage);

        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.execute(() -> {
                try {
                    imageRepo.save(xrayImage);
                    diagnosisRepo.save(diagnosis);
                } catch (DataIntegrityViolationException e) {
                    log.warn("Attempted to store already existing image with id: {}", id);
                }
            });
        }
    }
}
