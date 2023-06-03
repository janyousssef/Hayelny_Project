package com.hayelny.core.images;

import com.hayelny.core.diagnosis.Diagnosis;
import com.hayelny.core.diagnosis.DiagnosisController;
import com.hayelny.core.diagnosis.DiagnosisDTO;
import com.hayelny.core.diagnosis.DiagnosisRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/images")
@CrossOrigin
public class ImageController {
    private final ImageStorageService storageService;
    private final DiagnosisRepo diagnosisRepo;

    public ImageController(ImageStorageService storageService, DiagnosisRepo diagnosisRepo) {
        this.storageService = storageService;
        this.diagnosisRepo = diagnosisRepo;
    }


    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) {
        String imageId = storageService.persist(image);
        ImageDTO dto = new ImageDTO("Image uploaded successfully");

        EntityModel<ImageDTO> entityModel = EntityModel.of(dto)
                .add(linkTo(methodOn(ImageController.class).getImage(String.valueOf(imageId))).withRel("self"))
                .add(linkTo(methodOn(ImageController.class).getDiagnosis(imageId)).withRel("diagnosis"));

        return ResponseEntity.status(201)
                .body(entityModel);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        byte[] bytes = storageService.getImageAsBytes(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @GetMapping(value = "/{id}/diagnosis")
    public EntityModel<DiagnosisDTO> getDiagnosis(@PathVariable String id) {
        Diagnosis diagnosis = diagnosisRepo.findByImage_Id(id)
                .orElseThrow(() -> new EntityNotFoundException("No diagnosis for image with id = " + id + " found."));

        DiagnosisDTO dto = DiagnosisDTO.from(diagnosis);
        EntityModel<DiagnosisDTO> entityModel = EntityModel.of(dto);

        entityModel.add(linkTo(methodOn(ImageController.class).getImage(dto.getImageId())).withRel("image"));
        entityModel.add(linkTo(methodOn(DiagnosisController.class).getDiagnosis(dto.getDiagnosisId())).withRel("self"));

        return entityModel;
    }

}
