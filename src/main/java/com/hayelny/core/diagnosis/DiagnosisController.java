package com.hayelny.core.diagnosis;

import com.hayelny.core.images.ImageController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/diagnosis")
@CrossOrigin
public class DiagnosisController {
    private final DiagnosisRepo diagnosisRepo;

    public DiagnosisController(DiagnosisRepo diagnosisRepo) {
        this.diagnosisRepo = diagnosisRepo;
    }

    private static Supplier<EntityNotFoundException> notFound(Long id) {
        return () -> new EntityNotFoundException("No diagnosis for image with id = " + id + " found.");
    }

    @GetMapping(value = "/{id}")
    public EntityModel<DiagnosisDTO> getDiagnosis(@PathVariable Long id) {
        Diagnosis diagnosis = diagnosisRepo.findById(id)
                .orElseThrow(notFound(id));

        DiagnosisDTO dto = DiagnosisDTO.from(diagnosis);
        EntityModel<DiagnosisDTO> entityModel = EntityModel.of(dto);

        entityModel.add(linkTo(methodOn(ImageController.class).getImage(dto.getImageId())).withRel("image"));
        entityModel.add(linkTo(methodOn(DiagnosisController.class).getDiagnosis(dto.getDiagnosisId())).withRel("self"));
        return entityModel;
    }
}
