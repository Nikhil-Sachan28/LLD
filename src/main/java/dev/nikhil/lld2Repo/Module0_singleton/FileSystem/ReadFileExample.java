package dev.nikhil.lld2Repo.Module0_singleton.FileSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFileExample {
    private static final Logger log = LoggerFactory.getLogger(ReadFileExample.class);

    public static void main(String[] args){
        String fileName = "src/main/java/dev/nikhil/lld2Repo/Module0_singleton/FileSystem/Nikhil_Sachan_Resume_GenAI.pdf";

        try(FileInputStream fis = new FileInputStream(fileName)){
            int byteData;
            while((byteData = fis.read()) != -1){
                System.out.println(byteData);
            }
        }catch (IOException ex){
            log.error("e: ", ex);
        }


    }
}
