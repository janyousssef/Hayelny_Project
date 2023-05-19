package com.hayelny.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@ServletComponentScan
public class HayelnyApplication {



    public static void main(String[] args) {
        createImageDirectoryIfMissing();
        setParallelism();
        SpringApplication.run(HayelnyApplication.class,
                              args);
    }

    private static void setParallelism() {
        //Ensure that the parallelism level is at least 4
        int availableProcessors = Runtime
                .getRuntime()
                .availableProcessors();
        String desiredParallelism = availableProcessors > 4 ? String.valueOf(availableProcessors) : "4";
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                           desiredParallelism);
    }

    private static void createImageDirectoryIfMissing() {
        final Path IMAGE_DIRECTORY = Path.of("../images");

        if (Files.notExists(IMAGE_DIRECTORY)) {
            try {
                Files.createDirectory(IMAGE_DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }
}
