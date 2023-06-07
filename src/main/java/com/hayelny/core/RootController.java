package com.hayelny.core;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class RootController {

    public RootController() {
    }

    @GetMapping(path = "")
    @ResponseBody
    public ResponseEntity<?> index() {
        Message welcomeToHayelny = new Message("Welcome to Hayelny!");
        EntityModel<Map<String, String>> entityModel = EntityModel
                .of(welcomeToHayelny.content())
                .add(Link.of("/images").withRel("images"))
                .add(Link.of("/patients").withRel("patients"))
                .add(Link.of("/doctors").withRel("doctors"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    @RequestMapping(path = "/upload")
    public String uploadImage() {
        return "upload.html";
    }
    @GetMapping
    @RequestMapping(path = "/viewresult/{id}")
    public String viewResult(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "/result.html";
    }
}
