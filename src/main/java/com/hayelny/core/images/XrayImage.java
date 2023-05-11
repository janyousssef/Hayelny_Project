package com.hayelny.core.images;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class XrayImage {
    @Id
    @GeneratedValue
    private Long id;
    private String imagePath;

}
