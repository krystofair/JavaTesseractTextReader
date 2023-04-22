package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


public class TextFromJPGReaderMain {

    private static final String JPG_FILE_NAME = "kocham cie";
    private static final String TESSDATA_PATH = "C:\\Users\\fikot\\IdeaProjects\\text-from-jpg-reader\\tessdata";
    private static final String TESSERACT_LANGUAGE = "pol";

    public static void main(String[] args) throws TesseractException {

        File imageFile = new File("src/main/resources/" + JPG_FILE_NAME + ".jpg");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(TESSDATA_PATH);
        tesseract.setLanguage(TESSERACT_LANGUAGE);
        String result = tesseract.doOCR(imageFile);
        System.out.println(result);
    }
}