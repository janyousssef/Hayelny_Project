package com.hayelny.core.images;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class XrayImage {
    @Id
    private  String id;
    private String imagePath;

}
