package com.hayelny.core.images;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ImageConverter {
    private final String[] CMD_ARR = {"/bin/bash", "-c", "dcmj2pnm ","+oj"," +Jq ","95 ","",""};


    public void convertToJpeg(String path) {
        //requires dcmj2pnm to be installed
        //runs on linux
        CMD_ARR[CMD_ARR.length-2] = path;
        CMD_ARR[CMD_ARR.length-1] = path + ".jpg";
        System.out.println(Arrays.toString(CMD_ARR));
        System.out.println("converting image");
        try {
            Process exec = Runtime.getRuntime().exec(CMD_ARR);
            exec.waitFor();
            System.out.println(exec.exitValue());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("converting image done");
    }
}
