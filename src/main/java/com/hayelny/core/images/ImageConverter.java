package com.hayelny.core.images;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageConverter {
    //requires dcmj2pnm to be installed
    //runs on linux
    private final String[] CMD_ARR = {"/bin/bash", "-c", "dcmj2pnm +oj +Jq 95 "};

    public void convertToJpeg(String path) {
        CMD_ARR[2] = CMD_ARR[2] + path + " " + path;
        System.out.println(CMD_ARR[2]);
        System.out.println("converting image");
        try {
            Runtime.getRuntime().exec(CMD_ARR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("converting image done");
    }
}
