package com.hayelny.core.images;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImageConverter {


    public void convertToJpeg(String path) {
        //requires dcmj2pnm to be installed
        //runs on linux only
        String command = "dcmj2pnm +oj +Jq 95 " + path + " " + path + ".jpg";
        List<String> cmds = List.of("/bin/bash", "-c", command);
        System.out.println(cmds);
        System.out.println("converting image");
        try {
            Process exec = new ProcessBuilder().inheritIO()
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .command(cmds)
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
