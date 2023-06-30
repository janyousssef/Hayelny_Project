package com.hayelny.core.images;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageConverter {
    private final List<String> CMD_ARR = new ArrayList<>(List.of("/bin/bash",
                                                                 "-c",
                                                                 "dcmj2pnm",
                                                                 "+oj",
                                                                 "+Jq ",
                                                                 "95",
                                                                 "",
                                                                 ""));


    public void convertToJpeg(String path) {
        //requires dcmj2pnm to be installed
        //runs on linux
        CMD_ARR.add(path);
        CMD_ARR.add(path + ".jpg");
        System.out.println(CMD_ARR);
        System.out.println("converting image");
        try {
            Process exec = new ProcessBuilder().inheritIO()
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .command(CMD_ARR)
                    .start();
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
