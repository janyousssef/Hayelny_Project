package com.hayelny.core.images;

import com.hayelny.core.Message;
import com.hayelny.core.diagnosis.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/images")
@CrossOrigin
public class ImageController {
    private final ImageStorageService storageService;
    private final DiagnosisService diagnosisService;


    public ImageController(ImageStorageService storageService, DiagnosisService diagnosisService) {
        this.storageService = storageService;
        this.diagnosisService = diagnosisService;
    }


    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) {
        String imageId = storageService.persist(image);
        diagnosisService.diagnose(imageId);


        Message msg = new Message("id",imageId);
        //Add links to the response
        EntityModel<Map<String, String>> entityModel = EntityModel.of(msg.content())
                .add(linkTo(methodOn(ImageController.class).getImage(imageId)).withRel("self"))
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
        Diagnosis diagnosis = diagnosisService.findByImageId(id);

        DiagnosisDTO dto = DiagnosisDTO.from(diagnosis);
        EntityModel<DiagnosisDTO> entityModel = EntityModel.of(dto);

        entityModel.add(linkTo(methodOn(ImageController.class).getImage(dto.getImageId())).withRel("image"));
        entityModel.add(linkTo(methodOn(DiagnosisController.class).getDiagnosis(dto.getDiagnosisId())).withRel("self"));

        return entityModel;
    }

}
