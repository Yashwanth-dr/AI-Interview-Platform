package com.example.backendresume.services;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PDFService {

    public String extractText(
            String filePath
    ) throws Exception {

        File file = new File(filePath);

        PDDocument document =
                Loader.loadPDF(file);

        PDFTextStripper stripper =
                new PDFTextStripper();

        String text =
                stripper.getText(document);

        document.close();

        return text;
    }
}