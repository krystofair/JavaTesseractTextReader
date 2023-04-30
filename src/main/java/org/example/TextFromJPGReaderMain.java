package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;


public class TextFromJPGReaderMain {

    // this is set up in start script in linux by TESSDATA_PREFIX environment variable
    // private static final String TESSDATA_PATH = "tessdata/";
    private static final String TESSERACT_LANGUAGE = "pol";

    public static void main(String[] args) throws TesseractException {
        if (args.length == 0) {
            System.out.println("You have to pass filename of picture as an positional argument.");
            System.out.println("Example: java -jar programName img_file");
            System.exit(-1);
        }
        // special path I created for pictures to process
        // in logic: webserver put file to that folder and then program process it 
        // XXX getDefault() in cloud solution can be a problem?
        Path path = FileSystems.getDefault().getPath("/srv/untrusted/pictures/", args[0]);
        if (Files.exists(path)) {
            File imageFile = new File(path.toString());
            Tesseract tesseract = new Tesseract();
            // tesseract.setDatapath(TESSDATA_PATH);
            tesseract.setLanguage(TESSERACT_LANGUAGE);
            String result = tesseract.doOCR(imageFile);
            System.out.println(result);
        } else {
            System.out.println("The file " + path.toString() + " you provided is unavailable");
            System.exit(-1);
        }
        System.exit(0);
    }
}
