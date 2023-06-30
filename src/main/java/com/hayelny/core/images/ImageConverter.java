package com.hayelny.core.images;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageConverter {
    private final String[] CMD_ARR = {"/bin/bash", "-c", ""};

    public void convertToJpeg(String path) {
        //requires dcmj2pnm to be installed
        //runs on linux
        String cmd = "dcmj2pnm +oj +Jq 95 ";
        CMD_ARR[2] = cmd + path + " " + path + ".jpg";
        System.out.println(CMD_ARR[2]);
        System.out.println("converting image");
        try {
            Runtime.getRuntime().exec(CMD_ARR);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("converting image done");
    }
}
