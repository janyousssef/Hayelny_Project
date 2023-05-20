package com.hayelny.core.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo  extends JpaRepository<XrayImage, String> {
}
