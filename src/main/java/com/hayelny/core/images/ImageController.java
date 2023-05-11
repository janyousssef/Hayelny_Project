package com.hayelny.core.images;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/images")
public class ImageController {
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        System.out.println(image.getContentType());
        image.transferTo(Path.of("../images/" + Arrays.hashCode(image.getBytes()) + ".png"));
        return ResponseEntity.created(URI.create("/images/1"))
                .build();
    }

}
