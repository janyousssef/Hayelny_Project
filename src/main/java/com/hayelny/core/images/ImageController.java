package com.hayelny.core.images;

import com.hayelny.core.diagnosis.Diagnosis;
import com.hayelny.core.diagnosis.DiagnosisDTO;
import com.hayelny.core.diagnosis.DiagnosisRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/images")
public class ImageController {
    private final StorageService storageService;
    private final DiagnosisRepo diagnosisRepo;

    public ImageController(StorageService storageService, DiagnosisRepo diagnosisRepo) {
        this.storageService = storageService;
        this.diagnosisRepo = diagnosisRepo;
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {

        int imageId = storageService.storeImageAsLocalFile(image);
        storageService.persistInDB(imageId);
        var dto = new ImageDTO("Image uploaded successfully");
        EntityModel<ImageDTO> entityModel = EntityModel
                .of(dto)
                .add(linkTo(methodOn(ImageController.class).getImages(String.valueOf(imageId))).withRel("image"))
                .add(linkTo(methodOn(ImageController.class).getDiagnosis( imageId)).withRel("diagnosis"));
        return ResponseEntity
                .status(201)
                .body(entityModel);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImages(@PathVariable String id) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of(".." + File.separator + "images" + File.separator + id + ".png"));
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @GetMapping(value = "/{id}/diagnosis")
    public DiagnosisDTO getDiagnosis(@PathVariable int id) {
        Diagnosis diagnosis = diagnosisRepo
                .findByImage_Id(id)
                .orElseThrow(() -> new EntityNotFoundException("No diagnosis for image with id = " + id + " found."));

        return DiagnosisDTO.from(diagnosis);
    }

}
