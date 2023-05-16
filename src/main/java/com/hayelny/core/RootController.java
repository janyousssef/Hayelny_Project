package com.hayelny.core;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

record RootContextDTO(String message) {
}

@RestController
@RequestMapping(value = "/")
public class RootController {
    @GetMapping(path = "")
    public ResponseEntity<?> index() {
        RootContextDTO welcomeToHayelny = new RootContextDTO("Welcome to Hayelny!");
        EntityModel<RootContextDTO> entityModel = EntityModel
                .of(welcomeToHayelny)
                .add(Link
                             .of("/images")
                             .withRel("images"))
                .add(Link
                             .of("/patients")
                             .withRel("patients"))
                .add(Link
                             .of("/doctors")
                             .withRel("doctors"));
        return ResponseEntity.ok(entityModel);
    }
}
