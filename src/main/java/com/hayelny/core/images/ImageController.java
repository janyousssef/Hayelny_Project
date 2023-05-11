package com.hayelny.core.images;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "/images")
public class ImageController {
    private final StorageService storageService;

    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {

        int imageId = storageService.storeImageAsLocalFile(image);
        storageService.persistInDB(imageId);
        return ResponseEntity.status(201)
                .body("http://localhost:8080/images/" + imageId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImages(@PathVariable String id) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of(".." + File.separator + "images" + File.separator + id + ".png"));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

}