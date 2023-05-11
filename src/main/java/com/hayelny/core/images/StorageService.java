package com.hayelny.core.images;

import com.hayelny.core.diagnosis.Diagnosis;
import com.hayelny.core.diagnosis.DiagnosisRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@Service
public class StorageService {
    private static final Path IMAGE_DIRECTORY = Path.of(".." + File.separator + "images");
    private final DiagnosisRepo diagnosisRepo;
    private final ImageRepo imageRepo;

    public StorageService(DiagnosisRepo diagnosisRepo, ImageRepo imageRepo) {
        this.diagnosisRepo = diagnosisRepo;
        this.imageRepo = imageRepo;
    }

    public int storeImageAsLocalFile(MultipartFile image) throws IOException {

        int id = Arrays.hashCode(image.getBytes());
        String imagePath = IMAGE_DIRECTORY + File.separator + id + ".png";
        image.transferTo(Path.of(imagePath));
        return id;
    }

    public void persistInDB(int id) {
        Diagnosis diagnosis = new Diagnosis();
        XrayImage xrayImage = new XrayImage();
        String imagePath = IMAGE_DIRECTORY + File.separator + id + ".png";
        xrayImage.setImagePath(imagePath);
        xrayImage.setId((long) id);
        diagnosis.setImage(xrayImage);
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.execute(() -> {
                imageRepo.save(xrayImage);
                diagnosisRepo.save(diagnosis);
            });
        }
    }
}
